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

    /**
     * 分页查询角色列表
     * @param param
     * @return
     */
    @PostMapping(value = "/roleList")
    public Result<PageResult> getRoleListByPage(@RequestBody PageSearch<RoleListParamVO> param){
        return roleService.getRoleListByPage(param);
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping(value = "/add_role")
    public Result addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @PostMapping(value = "/edit_role")
    public Result editRole(@RequestBody Role role){
        return roleService.editRole(role);
    }

    /**
     * 获取当前角色的资源
     * @param id 角色id
     * @param platform 平台
     * @return
     */
    @GetMapping(value = "/resources/{id}")
    public Result<List<Long>> getResources(@PathVariable("id")Long id, @PathParam("platform")String platform){
        return roleService.getResourcesById(id,platform);
    }

    /**
     * 编辑当前角色的资源
     * @param ids 需要新增或删除的资源id
     * @param roleId 角色id
     * @return
     */
    @PostMapping(value = "/resource/edit/{id}")
    public Result editResourceByIds(@RequestBody Map<String,List<Long>> ids, @PathVariable("id")Long roleId){
        return roleService.editResourceByIds(ids.get("add"),ids.get("del"),roleId);
    }

}
