package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Resource;
import top.baozoulolw.exam.service.ResourceService;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 系统资源对应controller
 * 
 * @author baozoulolw
 * @version 1.0
 * @date 2022-02-06 18:03
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @javax.annotation.Resource
    private ResourceService resourceService;

    /**
     * 根据用户id获取用户权限对应的权限列表
     * @param id 用户id
     * @param platform 平台
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result<List<Resource>> getResByRole(@PathVariable(value = "id")Long id, @PathParam("platform")String platform){
        return resourceService.getResById(id,platform);
    }

    /**
     * 添加资源
     * @param resource
     * @return
     */
    @PostMapping(value = "/add")
    public Result addResource(@RequestBody Resource resource){
        return resourceService.addResource(resource);
    }

    /**
     * 更新资源
     * @param resource
     * @return
     */
    @PostMapping(value = "/update")
    public Result updateResource(@RequestBody Resource resource){
        return resourceService.updateResource(resource);
    }

    /**
     * 根据平台获取平台下所有资源列表
     * @param platform
     * @return
     */
    @GetMapping(value = "/getAll/{platform}")
    public Result<List<Resource>> getAllResource(@PathVariable("platform")String platform){
        return resourceService.getAllResource(platform);
    }

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Result delResource(@PathVariable("id")Long id){
        return resourceService.delResource(id);
    }


}
