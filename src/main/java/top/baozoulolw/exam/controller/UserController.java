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

    @PostMapping(value="/userList")
    public Result<PageResult> getUserListByPage(@RequestBody PageSearch<UserLIstParamVO> param){
        return userService.queryUserList(param);
    }

    @PostMapping(value = "/avatar/add")
    public Result uploadOssFile(MultipartFile avatar){
        return userService.uploadAvatar(avatar);
    }

    @GetMapping(value = "/check/{username}")
    public Result checkUsername(@PathVariable("username") String username){
        return userService.checkUsername(username);
    }

    @PostMapping(value="/add")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping(value = "/avatar/del/{fileName}")
    public Result delOssFile(@PathVariable("fileName") String fileName){
        return userService.delFile(fileName);
    }
}
