package top.baozoulolw.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.service.RoleService;
import top.baozoulolw.exam.vo.RoleListParamVO;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/role")
@Api("用于角色相关的配置")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping(value = "/roleList")
    public Result<PageResult> getRoleListByPage(@RequestBody PageSearch<RoleListParamVO> param){
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

    @GetMapping(value = "/resources/{id}")
    public Result<List<Long>> getResources(@PathVariable("id")Long id, @PathParam("platform")String platform){
        return roleService.getResourcesById(id,platform);
    }

    @PostMapping(value = "/resource/edit/{id}")
    public Result editResourceByIds(@RequestBody Map<String,List<Long>> ids, @PathVariable("id")Long roleId){
        return roleService.editResourceByIds(ids.get("add"),ids.get("del"),roleId);
    }

}
