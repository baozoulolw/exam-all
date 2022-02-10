package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Role;
import top.baozoulolw.exam.vo.RoleListParamVO;

import java.util.List;

public interface RoleService {

    /**
     * 分页查询角色列表
     * @param param
     * @return
     */
    Result<PageResult> getRoleListByPage(PageSearch<RoleListParamVO> param);

    /**
     * 添加角色
     * @param role
     * @return
     */
    Result addRole(Role role);

    /**
     * 编辑角色
     * @param role
     * @return
     */
    Result editRole(Role role);

    /**
     * 获取当前角色的资源id集合
     * @param id 角色id
     * @param platform 平台
     * @return
     */
    Result<List<Long>> getResourcesById(Long id,String platform);

    /**
     * 编辑当前角色的资源
     * @param add 需要新增的资源id
     * @param del 需要删除的资源id
     * @param roleId 角色id
     * @return
     */
    Result editResourceByIds(List<Long> add, List<Long> del, Long roleId);
}
