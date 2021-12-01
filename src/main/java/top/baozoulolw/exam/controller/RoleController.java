package top.baozoulolw.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.service.RoleService;
import top.baozoulolw.exam.vo.RoleListParam;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/role")
@Api("用于角色相关的配置")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping(value = "/roleList")
    public Result<PageResult> getRoleListByPage(@RequestBody PageSearch<RoleListParam> param){
        return roleService.getRoleListByPage(param);
    }

    @PostMapping(value = "/add_role")
    public Result addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @PostMapping(value = "/edit_role")
    public Result editRole(@RequestBody Role role){
        return roleService.editRole(role);
    }
}
