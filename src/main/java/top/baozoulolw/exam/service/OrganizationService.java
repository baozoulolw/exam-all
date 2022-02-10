package top.baozoulolw.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Organization;

import java.util.List;

@Service
public interface OrganizationService extends IService<Organization> {
    Result addOrganization(Organization organization);

    Result updateOrganization(Organization organization);

    Result delOrganization(Long id);

    Result<List<Organization>> getOrganizationList();
}
