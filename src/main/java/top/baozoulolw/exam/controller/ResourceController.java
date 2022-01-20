package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Resource;
import top.baozoulolw.exam.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @javax.annotation.Resource
    private ResourceService resourceService;

    @GetMapping(value = "/{id}")
    public Result<List<Resource>> getResByRole(@PathVariable(value = "id")Long id){
        return resourceService.getResById(id);
    }

    @PostMapping(value = "/add")
    public Result addResource(@RequestBody Resource resource){
        return resourceService.addResource(resource);
    }

    @PostMapping(value = "/update")
    public Result updateResource(@RequestBody Resource resource){
        return resourceService.updateResource(resource);
    }

    @GetMapping(value = "/getAll")
    public Result<List<Resource>> getAllResource(){
        return resourceService.getAllResource();
    }

    @GetMapping(value = "/del/{id}")
    public Result delResource(@PathVariable("id")Long id){
        return resourceService.delResource(id);
    }
}
