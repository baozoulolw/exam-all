package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.dao.ResourceDao;
import top.baozoulolw.exam.dao.RoleResourceDao;
import top.baozoulolw.exam.entity.Resource;
import top.baozoulolw.exam.entity.RoleResource;
import top.baozoulolw.exam.service.ResourceService;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

    @javax.annotation.Resource
    private ResourceDao resourceDao;

    @javax.annotation.Resource
    private RoleResourceDao roleResourceDao;

    /**
     * 根据用户id获取用户权限对应的权限列表
     * @param id 用户id
     * @param platform 平台
     * @return
     */
    @Override
    public Result<List<Resource>> getResById(Long id,String platform) {
        List<Resource> resourceByUserId = resourceDao.getResourceByUserId(id,platform);
        return Result.success(resourceByUserId);
    }

    /**
     * 添加资源
     * @param resource
     * @return
     */
    @Override
    public Result addResource(Resource resource) {
        boolean save = this.save(resource);
        if(!save){
            return Result.fail("新增资源失败");
        }
        return Result.success();
    }

    /**
     * 更新资源
     * @param resource
     * @return
     */
    @Override
    public Result updateResource(Resource resource) {
        boolean b = this.updateById(resource);
        if(!b){
            return Result.fail("更新资源失败");
        }
        return Result.success();
    }


    /**
     * 根据平台获取平台下所有资源列表
     * @param platform
     * @return
     */
    @Override
    public Result<List<Resource>> getAllResource(String platform) {
        List<Resource> allResource = resourceDao.getAllResource(platform);
        return Result.success(allResource);
    }

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    @Override
    public Result delResource(Long id) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent",id);
        Page<Resource> resourcePage = resourceDao.selectPage(new Page<>(1, 1), wrapper);
        if(resourcePage.getRecords().size() > 0){
            return Result.fail("该资源已有子资源关联，无法删除");
        }
        QueryWrapper<RoleResource> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("resource_id", id);
        Page<RoleResource> roleResourcePage = roleResourceDao.selectPage(new Page<>(1, 1), wrapper1);
        if(roleResourcePage.getRecords().size() > 0){
            return Result.fail("该资源已有角色与其关联，无法删除");
        }
        resourceDao.deleteById(id);
        return Result.success();
    }
}
