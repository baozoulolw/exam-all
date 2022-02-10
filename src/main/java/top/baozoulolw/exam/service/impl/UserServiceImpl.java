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
import top.baozoulolw.exam.dao.ResourceDao;
import top.baozoulolw.exam.dao.RoleDao;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.utils.FileUploadUtil;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private ResourceDao resourceDao;

    /**
     * spring security使用的用来验证用户账号密码的service
     * @param username 登录用户名
     * @return
     * @throws UsernameNotFoundException
     */
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

    /**
     * 分页查询用户列表
     * @param param 分页参数
     * @return
     */
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


    /**
     * 验证用户名是否重复
     * @param username 待验证用户名
     * @return
     */
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


    /**
     * 添加用户
     * @param user
     * @return
     */
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


    /**
     * 上传头像至oss
     * @param file
     * @return
     */
    @Override
    public Result uploadAvatar(MultipartFile file) {
        String path = "exam/avatar";
        String fileName = path + "/" + fileUploadUtil.genFilename();
        fileUploadUtil.upload(file, fileName);
        return Result.success(fileName);
    }


    /**
     * 根据文件名删除oss服务器文件
     * @param fileName
     * @return
     */
    @Override
    public Result delFile(String fileName) {
        fileUploadUtil.delFile(fileName);
        return Result.success();
    }

    /**
     * 判断用户是否有当前平台下的资源
     * @param id 用户id
     * @param platform 平台
     * @return true：有资源 false：无资源
     */
    @Override
    public Boolean hasResource(Long id,String platform) {
        List<Long> ids = resourceDao.hasResource(id, platform);
        return ids.size() > 0;
    }
}
