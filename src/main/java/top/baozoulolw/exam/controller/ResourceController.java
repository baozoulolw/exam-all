package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
