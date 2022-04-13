package top.baozoulolw.exam.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.entity.UserGroup;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 分页查询用户列表
     * @param param 分页参数
     * @return
     */
    Result<PageResult> queryUserList(PageSearch<UserLIstParamVO> param);

    /**
     * 验证用户名是否重复
     * @param username 待验证用户名
     * @return
     */
    Result checkUsername(String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Result addUser(User user);

    /**
     * 上传头像至oss
     * @param file
     * @return
     */
    Result uploadAvatar(MultipartFile file);

    /**
     * 根据文件名删除oss服务器文件
     * @param fileName
     * @return
     */
    Result delFile(String fileName);

    /**
     * 判断用户是否有当前平台下的资源
     * @param id 用户id
     * @param platform 平台
     * @return true：有资源 false：无资源
     */
    Boolean hasResource(Long id,String platform);

    /**
     * 获取成员分类列表
     * @return
     */
    Result<List<UserGroup>> getGroupList();

    /**
     * 编辑成员分类
     * @param userGroup
     * @return
     */
    Result editGroup(UserGroup userGroup);

    /**
     * 添加成员分类
     * @param groupName
     * @return
     */
    Result addGroup(String groupName);

    /**
     * 根据id删除成员分类
     * @param id
     * @return
     */
    Result delGroup(Long id);

    /**
     * 分类下的成员进行转移
     * @param from
     * @param to
     * @return
     */
    Result transGroup(Long from, Long to);

    Result<User> getUserSelf();

    Result<User> updateUser(User user);

    Result editPassword(User user);

    Result bindRole(Long roleId, Long userId, int type);

    Result<List<Long>> getCourse(Long id);

    Result bindCourse(Long roleId, Long userId, int type);

    Result<List<QuestionGroup>> getCourseAll();
}
