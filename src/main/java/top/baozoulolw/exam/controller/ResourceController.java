package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Resource;
import top.baozoulolw.exam.service.ResourceService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @javax.annotation.Resource
    private ResourceService resourceService;

    @GetMapping(value = "/{id}")
    public Result<List<Resource>> getResByRole(@PathVariable(value = "id")Long id, @PathParam("platform")String platform){
        return resourceService.getResById(id,platform);
    }

    @PostMapping(value = "/add")
    public Result addResource(@RequestBody Resource resource){
        return resourceService.addResource(resource);
    }

    @PostMapping(value = "/update")
    public Result updateResource(@RequestBody Resource resource){
        return resourceService.updateResource(resource);
    }

    @GetMapping(value = "/getAll/{platform}")
    public Result<List<Resource>> getAllResource(@PathVariable("platform")String platform){
        return resourceService.getAllResource(platform);
    }

    @GetMapping(value = "/del/{id}")
    public Result delResource(@PathVariable("id")Long id){
        return resourceService.delResource(id);
    }


}
