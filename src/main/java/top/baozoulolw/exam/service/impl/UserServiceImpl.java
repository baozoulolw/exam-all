package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.RoleDao;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

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

    @Override
    public Result<PageResult> queryUserList(PageSearch<UserLIstParamVO> param) {
        Page<User> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<User> userList = userDao.getUserList(page, param.getParam());
        PageResult pageResult = new PageResult(userList);
        System.out.println(userList);
        return Result.success(pageResult);
    }
}
