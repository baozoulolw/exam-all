package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.enums.ResultCode;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.*;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.entity.RoleResource;
import top.baozoulolw.exam.entity.UserRole;
import top.baozoulolw.exam.service.RoleService;
import top.baozoulolw.exam.vo.RoleListParamVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleResourceDao roleResourceDao;

    @Resource
    private ResourceDao resourceDao;


    /**
     * 分页查询角色列表
     * @param param
     * @return
     */
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

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public Result addRole(Role role) {
        String roleName = role.getRoleName();
        if (StringUtils.isBlank(roleName)) {
            return Result.fail(ResultCode.PARAM_IS_INVALID, "角色名不能为空");
        }
        int count = roleDao.selectCount(new QueryWrapper<Role>().eq("role_name", roleName));
        if (count > 0) {
            return Result.fail(ResultCode.PARAM_IS_INVALID, "当前角色名已存在");
        }
        int insert = roleDao.insert(role);
        if (insert == 0) {
            return Result.fail(ResultCode.SYSTEM_ERROR);
        }
        return Result.success();
    }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @Override
    public Result editRole(Role role) {
        String roleName = role.getRoleName();
        if (StringUtils.isBlank(roleName)) {
            return Result.fail(ResultCode.PARAM_IS_INVALID, "角色名不能为空");
        }
        int count = roleDao.selectCount(new QueryWrapper<Role>().eq("role_name", roleName).ne("id", role.getId()));
        if (count > 0) {
            return Result.fail(ResultCode.PARAM_IS_INVALID, "当前角色名已存在");
        }
        roleDao.updateById(role);
        return Result.success();
    }

    /**
     * 获取当前角色的资源id集合
     * @param id 角色id
     * @param platform 平台
     * @return
     */
    @Override
    public Result<List<Long>> getResourcesById(Long id,String platform) {
        List<Long> keys = resourceDao.checkKeys(id, platform);
        return Result.success(keys);
    }

    /**
     * 编辑当前角色的资源
     * @param add 需要新增的资源id
     * @param del 需要删除的资源id
     * @param roleId 角色id
     * @return
     */
    @Override
    public Result editResourceByIds(List<Long> add, List<Long> del, Long roleId) {
        add.forEach(i -> {
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(i);
            roleResource.setRoleId(roleId);
            roleResourceDao.insert(roleResource);
        });
        if (del.size() > 0) {
            UpdateWrapper<RoleResource> wrapper = new UpdateWrapper<>();
            wrapper.in("resource_id", del).eq("role_id", roleId);
            roleResourceDao.delete(wrapper);
        }
        return Result.success();
    }

    @Override
    public Result delRole(Long id) {
        QueryWrapper<UserRole> urWrapper = new QueryWrapper<>();
        urWrapper.eq("role_id",id);
        userRoleDao.delete(urWrapper);
        QueryWrapper<RoleResource> rrWrapper = new QueryWrapper<>();
        rrWrapper.eq("role_id",id);
        roleResourceDao.delete(rrWrapper);
        roleDao.deleteById(id);
        return Result.success();
    }

}
