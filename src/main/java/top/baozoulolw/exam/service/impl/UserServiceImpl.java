package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.User;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            throw new UsernameNotFoundException("用户名错误");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(username),User::getUsername,username);
        User user = userDao.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("找不到用户名为: " + username + "的用户");
        }
        return user;
    }
}
