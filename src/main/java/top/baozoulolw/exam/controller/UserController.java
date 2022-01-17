package top.baozoulolw.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.service.AliyunOssService;
import top.baozoulolw.exam.service.UserService;
import top.baozoulolw.exam.vo.UserLIstParamVO;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@Api("用于用户登录，获取用户信息等的接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private AliyunOssService aliyunOssService;

    @PostMapping(value="/userList")
    public Result<PageResult> getUserListByPage(@RequestBody PageSearch<UserLIstParamVO> param){
        return userService.queryUserList(param);
    }

    @PostMapping(value = "/fileoss")
    public Map<String, Object> uploadOssFile(MultipartFile file, String fileName){
        return aliyunOssService.upload(file, fileName);
    }

}
