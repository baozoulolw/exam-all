package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.RoleDao;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.service.RoleService;
import top.baozoulolw.exam.vo.RoleListParam;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Result<PageResult> getRoleListByPage(PageSearch<RoleListParam> param) {
        IPage<Role> page = new Page<>(param.getPageNumber(), param.getPageSize());
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(param.getParam().getRoleName()), "role_name", param.getParam().getRoleName());
        IPage<Role> result = roleDao.selectPage(page, wrapper);
        PageResult pageResult = new PageResult(result);
        return Result.success(pageResult);
    }
}
