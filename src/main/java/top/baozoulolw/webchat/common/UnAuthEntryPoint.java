package top.baozoulolw.webchat.common;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.baozoulolw.webchat.common.enums.ResultCode;
import top.baozoulolw.webchat.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//未认证时执行这个类
public class UnAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.out(response, Result.fail(ResultCode.USER_NEED_AUTHORITIES));
    }
}
