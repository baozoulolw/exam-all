package top.baozoulolw.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Exam;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.entity.UserGroup;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;
import java.util.List;

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
     * 获取自己的信息
     * @param
     * @return
     */
    @GetMapping(value="/self")
    public Result<User> getUserSelf(){
        return userService.getUserSelf();
    }

    /**
     * 根据id修改资料
     * @param
     * @return
     */
    @PostMapping(value="/edit")
    public Result<User> editUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 修改密码
     * @param
     * @return
     */
    @PostMapping(value="/edit/password")
    public Result editPassword(@RequestBody User user){
        return userService.editPassword(user);
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

    /**
     * 获取成员分类列表
     * @return
     */
    @GetMapping(value = "/group/list")
    public Result<List<UserGroup>> getGroupList() {
        return userService.getGroupList();
    }



    /**
     * 编辑成员分类
     * @param userGroup
     * @return
     */
    @PostMapping(value = "/group/edit")
    public Result editGroup(@RequestBody UserGroup userGroup) {
        return userService.editGroup(userGroup);
    }

    /**
     * 添加成员分类
     * @param groupName
     * @return
     */
    @GetMapping(value = "/group/add/{groupName}")
    public Result addGroup(@PathVariable("groupName")String groupName) {
        return userService.addGroup(groupName);
    }

    /**
     * 根据id删除成员分类
     * @param id
     * @return
     */
    @GetMapping(value = "/group/del/{id}")
    public Result delGroup(@PathVariable("id")Long id) {
        return userService.delGroup(id);
    }

    /**
     * 分类下的成员进行转移
     * @param from
     * @param to
     * @return
     */
    @GetMapping(value = "/group/trans")
    public Result transGroup(@RequestParam("from")Long from,@RequestParam("to")Long to) {
        return userService.transGroup(from,to);
    }

    /**
     *  用户角色绑定相关
     * @param roleId
     * @param userId
     * @param type 0解绑 1绑定
     * @return
     */
    @GetMapping(value = "/role/{roleId}/{userId}/{type}")
    public Result bindRole(@PathVariable("roleId")Long roleId,@PathVariable("userId")Long userId,@PathVariable("type")int type) {
        return userService.bindRole(roleId,userId,type);
    }
}
