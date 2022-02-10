package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Organization;
import top.baozoulolw.exam.service.OrganizationService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;

    /**
     * 添加组织
     * @param organization
     * @return
     */
    @PostMapping(value = "/add")
    public Result addOrganization(@RequestBody Organization organization){
        return organizationService.addOrganization(organization);
    }

    /**
     * 更新组织
     * @param organization
     * @return
     */
    @PostMapping(value = "/update")
    public Result updateOrganization(@RequestBody Organization organization){
        return organizationService.updateOrganization(organization);
    }

    /**
     * 删除组织
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Result delOrganization(@PathVariable("id")Long id){
        return organizationService.delOrganization(id);
    }

    /**
     * 获取组织树
     * @return
     */
    @GetMapping(value = "/list")
    public Result<List<Organization>> getOrganizationList(){
        return organizationService.getOrganizationList();
    }
}
