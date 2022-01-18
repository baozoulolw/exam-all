package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.RoleDao;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.utils.FileUploadUtil;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private FileUploadUtil fileUploadUtil;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名错误");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(username), User::getUsername, username);
        User user = userDao.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("找不到用户名为: " + username + "的用户");
        }
        user.setAvatarUrl(fileUploadUtil.getPreviewUrl(user.getAvatar()));
        return user;
    }

    @Override
    public Result<PageResult> queryUserList(PageSearch<UserLIstParamVO> param) {
        Page<User> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<User> userList = userDao.getUserList(page, param.getParam());
        PageResult pageResult = new PageResult(userList);
        userList.getRecords().forEach(item -> {
            item.setAvatarUrl(fileUploadUtil.getPreviewUrl(item.getAvatar()));
        });
        return Result.success(pageResult);
    }

    @Override
    public Result checkUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Page<User> userPage = userDao.selectPage(new Page<>(1, 1), wrapper);
        if (userPage.getRecords().size() > 0) {
            return Result.fail("当前用户名已被注册");
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result<PageResult> addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        int insert = userDao.insert(user);
        if (insert == 0) {
            if (StringUtils.isNotBlank(user.getAvatar())) {
                fileUploadUtil.delFile(user.getAvatar());
            }
            return Result.fail("创建失败，请重试");
        }
        return Result.success();
    }

    @Override
    public Result uploadAvatar(MultipartFile file) {
        String path = "exam/avatar";
        String fileName = path + "/" + fileUploadUtil.genFilename();
        fileUploadUtil.upload(file, fileName);
        return Result.success(fileName);
    }

    @Override
    public Result delFile(String fileName) {
        fileUploadUtil.delFile(fileName);
        return Result.success();
    }


}
