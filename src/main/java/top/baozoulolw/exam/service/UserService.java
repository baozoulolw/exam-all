package top.baozoulolw.exam.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.vo.UserLIstParamVO;

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
}
