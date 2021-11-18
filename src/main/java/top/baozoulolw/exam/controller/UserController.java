package top.baozoulolw.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/User")
@Api("用于用户登录，获取用户信息等的接口")
public class UserController {
}
