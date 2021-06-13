package top.baozoulolw.webchat.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.baozoulolw.webchat.common.Result;
import top.baozoulolw.webchat.common.enums.ResultCode;
import top.baozoulolw.webchat.entity.User;
import top.baozoulolw.webchat.service.UserService;
import top.baozoulolw.webchat.utils.JwtUtils;
import top.baozoulolw.webchat.utils.ResponseUtils;

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

    public JwtLoginFilter(AuthenticationManager authenticationManager,UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
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
            String password = obtainPassword(request);
            User user = (User) userService.loadUserByUsername(username);
            user.setPassword(null);
            Map<String, Object> claim = new HashMap<>(2);
            claim.put("username", username);
            claim.put("userId", user.getId());
            String token = JwtUtils.createJwt(claim, UUID.randomUUID().toString(), 1000 * 60 * 60 * 24L);
            Map<String, Object> result = new HashMap<>(2);
            result.put("token",token);
            result.put("user",user);
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
        ResponseUtils.out(response, Result.fail(ResultCode.LOGIN_FAILED));
    }
}
