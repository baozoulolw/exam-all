package top.baozoulolw.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
@Api("用于用户登录，获取用户信息等的接口")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询用户列表
     * @param param 分页参数
     * @return
     */
    @PostMapping(value="/userList")
    public Result<PageResult> getUserListByPage(@RequestBody PageSearch<UserLIstParamVO> param){
        return userService.queryUserList(param);
    }

    /**
     * 上传头像至oss服务器
     * @param avatar
     * @return
     */
    @PostMapping(value = "/avatar/add")
    public Result uploadOssFile(MultipartFile avatar){
        return userService.uploadAvatar(avatar);
    }

    /**
     * 验证用户名是否重复
     * @param username 待验证用户名
     * @return
     */
    @GetMapping(value = "/check/{username}")
    public Result checkUsername(@PathVariable("username") String username){
        return userService.checkUsername(username);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping(value="/add")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    /**
     * 根据文件名删除oss服务器文件
     * @param fileName
     * @return
     */
    @GetMapping(value = "/avatar/del/{fileName}")
    public Result delOssFile(@PathVariable("fileName") String fileName){
        return userService.delFile(fileName);
    }
}
