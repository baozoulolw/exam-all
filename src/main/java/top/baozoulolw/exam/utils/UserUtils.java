package top.baozoulolw.exam.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import top.baozoulolw.exam.entity.User;


public class UserUtils {

    /**
     * 获取当前线程操作的userid;
     * @return
     */
    public static Long getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Long) authentication.getPrincipal();
    }
}
