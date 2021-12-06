package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.vo.RoleListParamVO;

public interface RoleService {

    Result<PageResult> getRoleListByPage(PageSearch<RoleListParamVO> param);

    Result addRole(Role role);

    Result editRole(Role role);
}
