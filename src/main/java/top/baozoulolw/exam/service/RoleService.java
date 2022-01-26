package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.vo.RoleListParamVO;

import java.util.List;

public interface RoleService {

    Result<PageResult> getRoleListByPage(PageSearch<RoleListParamVO> param);

    Result addRole(Role role);

    Result editRole(Role role);

    Result<List<Long>> getResourcesById(Long id,String platform);

    Result editResourceByIds(List<Long> add, List<Long> del, Long roleId);
}
