package top.baozoulolw.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.baozoulolw.exam.handler.ChatLogoutSuccessHandler;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.common.UnAuthEntryPoint;
import top.baozoulolw.exam.filter.JwtLoginFilter;
import top.baozoulolw.exam.filter.JwtPreFilter;

import javax.annotation.Resource;

/**
 * Spring Security配置类
 *
 * @author Baozoulolw
 * @version 1.0
 * @date 2021-03-01 9:16
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    /**
     * 加载 userDetailsService，用于从数据库中取用户信息
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * 不进行认证的路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //巨坑，这里不能加上context-path:/chat，不然不能拦截
        web.ignoring().antMatchers("/chat"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域资源共享
        http.cors()
                .and().csrf().disable() // 关闭csrf
                // 关闭session（无状态）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests() //设置认证请求
                // 放行静态资源
                .antMatchers("/expression/**", "/face/**", "/img/**", "/uploads/**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessHandler(new ChatLogoutSuccessHandler()).and()
                // 先是UsernamePasswordAuthenticationFilter用于login校验
                .addFilter(new JwtLoginFilter(authenticationManager(),userService))
                // 再通过OncePerRequestFilter，对其它请求过滤
                .addFilter(new JwtPreFilter(authenticationManager()))
                .httpBasic().authenticationEntryPoint(new UnAuthEntryPoint()); //没有权限访问
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
