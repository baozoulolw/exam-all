package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.*;
import top.baozoulolw.exam.entity.*;
import top.baozoulolw.exam.service.UserGroupService;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.utils.FileUploadUtil;
import top.baozoulolw.exam.utils.UserUtils;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private FileUploadUtil fileUploadUtil;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private ResourceDao resourceDao;

    @Resource
    private QuestionGroupDao questionGroupDao;

    @Resource
    private UserCourseDao userCourseDao;

    @Resource
    private UserGroupService userGroupService;

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
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(user.getRoleIds().get(0));
        userRoleDao.insert(userRole);
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

    /**
     * 获取成员分类列表
     *
     * @return
     */
    @Override
    public Result<List<UserGroup>> getGroupList() {
        return Result.success(userDao.getUserGroupList());
    }

    /**
     * 编辑成员分类
     *
     * @param userGroup
     * @return
     */
    @Override
    public Result editGroup(UserGroup userGroup) {
        QueryWrapper<UserGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",userGroup.getGroupName());
        UserGroup one = userGroupService.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(one)){
            return Result.fail("分组名重复");
        }
        userGroupService.updateById(userGroup);
        return Result.success();
    }

    /**
     * 添加成员分类
     *
     * @param groupName
     * @return
     */
    @Override
    public Result addGroup(String groupName) {
        QueryWrapper<UserGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",groupName);
        UserGroup one = userGroupService.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(one)){
            return Result.fail("分组名重复");
        }
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName(groupName);
        userGroupService.save(userGroup);
        return Result.success();
    }

    /**
     * 根据id删除成员分类
     *
     * @param id
     * @return
     */
    @Override
    public Result delGroup(Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",id);
        Page<User> questionPage = userDao.selectPage(new Page<>(1, 1), wrapper);
        if(questionPage.getRecords().size() > 0){
            return Result.fail("当前分组下有试题，无法删除");
        }
        userGroupService.removeById(id);
        return Result.success();
    }

    /**
     * 分类下的成员进行转移
     *
     * @param from
     * @param to
     * @return
     */
    @Override
    public Result transGroup(Long from, Long to) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("group_id",from).set("group_id",to);
        userDao.update(null,wrapper);
        return Result.success();
    }

    @Override
    public Result<User> getUserSelf() {
        User user = userDao.selectById(UserUtils.getUserId());
        QueryWrapper<UserRole> wr = new QueryWrapper<>();
        wr.select("role_id").eq("user_id",user.getId());
        List<Object> objects = userRoleDao.selectObjs(wr);
        List<Long> collect = objects.stream().map(i -> (Long) i).collect(Collectors.toList());
        List<Role> roles = roleDao.selectBatchIds(collect);
        user.setRoles(roles);
        user.setAvatarUrl(fileUploadUtil.getPreviewUrl(user.getAvatar()));
        return Result.success(user);
    }

    @Override
    public Result<User> updateUser(User user) {
        userDao.updateById(user);
        return Result.success();
    }

    @Override
    public Result editPassword(User user) {
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setId(UserUtils.getUserId());
        userDao.updateById(user);
        return Result.success();
    }

    @Override
    public Result bindRole(Long roleId, Long userId, int type) {
        if(type == 1){
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleDao.insert(userRole);
        }else{
            QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).eq("role_id",roleId);
            userRoleDao.delete(wrapper);
        }
        return Result.success();
    }

    @Override
    public Result<List<Long>> getCourse(Long id) {
        QueryWrapper<UserCourse> wrapper = new QueryWrapper<>();
        wrapper.select("course_id").eq("user_id",id);
        return Result.success(userCourseDao.selectObjs(wrapper).stream().map(i -> (Long) i).collect(Collectors.toList()));
    }

    @Override
    public Result bindCourse(Long roleId, Long userId, int type) {
        if(type == 1){
            UserCourse userCourse = new UserCourse();
            userCourse.setCourseId(roleId);
            userCourse.setUserId(userId);
            userCourseDao.insert(userCourse);
        }else{
            QueryWrapper<UserCourse> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",userId).eq("course_id",roleId);
            userCourseDao.delete(wrapper);
        }
        return Result.success();
    }

    @Override
    public Result<List<QuestionGroup>> getCourseAll() {
        QueryWrapper<QuestionGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("parent", 2L);
        List<QuestionGroup> questionGroups = questionGroupDao.selectList(wrapper);
        return Result.success(questionGroups);
    }
}
