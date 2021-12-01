package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.vo.RoleListParam;

public interface RoleService {

    Result<PageResult> getRoleListByPage(PageSearch<RoleListParam> param);
}
