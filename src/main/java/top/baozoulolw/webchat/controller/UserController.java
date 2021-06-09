package top.baozoulolw.webchat.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baozoulolw.webchat.common.Result;
import top.baozoulolw.webchat.entity.User;

@RestController
@RequestMapping(value = "/User")
@Api(tags = "用于用户登录，获取用户信息等的接口")
public class UserController {
}
