package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.enums.ResultCode;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.RoleDao;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.service.RoleService;
import top.baozoulolw.exam.vo.RoleListParamVO;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    @Override
    public Result<PageResult> getRoleListByPage(PageSearch<RoleListParamVO> param) {
        IPage<Role> page = new Page<>(param.getPageNumber(), param.getPageSize());
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(param.getParam().getRoleName()), "role_name", param.getParam().getRoleName());
        IPage<Role> result = roleDao.selectPage(page, wrapper);
        result.getRecords().forEach(item -> {
            item.setChangeUserName(userDao.selectById(item.getOperUser()).getRealName());
        });
        PageResult pageResult = new PageResult(result);
        return Result.success(pageResult);
    }

    @Override
    public Result addRole(Role role) {
        String roleName = role.getRoleName();
        if(StringUtils.isBlank(roleName)){
            return Result.fail(ResultCode.PARAM_IS_INVALID,"角色名不能为空");
        }
        int count = roleDao.selectCount(new QueryWrapper<Role>().eq("role_name",roleName));
        if(count > 0){
            return Result.fail(ResultCode.PARAM_IS_INVALID,"当前角色名已存在");
        }
        int insert = roleDao.insert(role);
        if(insert == 0) {
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
        return Result.success();
    }

    @Override
    public Result editRole(Role role) {
        String roleName = role.getRoleName();
        if(StringUtils.isBlank(roleName)){
            return Result.fail(ResultCode.PARAM_IS_INVALID,"角色名不能为空");
        }
        int count = roleDao.selectCount(new QueryWrapper<Role>().eq("role_name",roleName).ne("id",role.getId()));
        if(count > 0){
            return Result.fail(ResultCode.PARAM_IS_INVALID,"当前角色名已存在");
        }
        roleDao.updateById(role);
        return Result.success();
    }
}
