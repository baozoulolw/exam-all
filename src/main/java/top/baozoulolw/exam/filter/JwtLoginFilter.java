package top.baozoulolw.exam.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.enums.ResultCode;
import top.baozoulolw.exam.utils.JwtUtils;
import top.baozoulolw.exam.utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private User user;



    public JwtLoginFilter(AuthenticationManager authenticationManager,UserService userService,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.setFilterProcessesUrl("/login");

    }

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String platform = request.getParameter("platform");
        this.user = (User) userService.loadUserByUsername(username);
        //验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("输入密码错误!");
        }
        //验证是否有登录系统的资源
        if(!userService.hasResource(user.getId(), platform)){
            throw new BadCredentialsException("没有当前系统资源!");
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));

    }

    /**
     * 登录验证成功后调用，验证成功后将生成Token，并重定向到用户主页home
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        try {
            String username = obtainUsername(request);
            //String password = obtainPassword(request);
            User user = this.user;
            user.setPassword(null);
            Map<String, Object> claim = new HashMap<>(2);
            claim.put("username", username);
            claim.put("userId", user.getId());
            String token = JwtUtils.createJwt(claim, UUID.randomUUID().toString(), 1000 * 60 * 60 * 24L);
            Map<String, Object> result = new HashMap<>(4);
            result.put("token",token);
            // 此处返回会直接response会跳过josn转换，导致id失真，故用string再封装属性
            String id = user.getId().toString();
            String operuser = user.getOperUser().toString();
            result.put("user",user);
            result.put("id",id);
            result.put("operuser",operuser);
            ResponseUtils.out(response, Result.success(ResultCode.LOGIN_SUCCESS,result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录验证失败后调用，这里直接Json返回，实际上可以重定向到错误界面等
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        ResponseUtils.out(response,Result.fail(failed.getMessage()));
    }
}
