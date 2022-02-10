package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.dao.OrganizationDao;
import top.baozoulolw.exam.entity.Organization;
import top.baozoulolw.exam.service.OrganizationService;

import java.util.List;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, Organization> implements OrganizationService {
    @Override
    public Result addOrganization(Organization organization) {
        return null;
    }

    @Override
    public Result updateOrganization(Organization organization) {
        return null;
    }

    @Override
    public Result delOrganization(Long id) {
        return null;
    }

    @Override
    public Result<List<Organization>> getOrganizationList() {
        return null;
    }
}
