package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.dao.ResourceDao;
import top.baozoulolw.exam.entity.Resource;
import top.baozoulolw.exam.service.ResourceService;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

    @javax.annotation.Resource
    private ResourceDao resourceDao;

    @Override
    public Result<List<Resource>> getResById(Long id) {
        return null;
    }

    @Override
    public Result addResource(Resource resource) {
        boolean save = this.save(resource);
        if(!save){
            return Result.fail("新增资源失败");
        }
        return Result.success();
    }

    @Override
    public Result updateResource(Resource resource) {
        boolean b = this.updateById(resource);
        if(!b){
            return Result.fail("更新资源失败");
        }
        return Result.success();
    }

    @Override
    public Result<List<Resource>> getAllResource() {
        List<Resource> allResource = resourceDao.getAllResource();
        return Result.success(allResource);
    }
}
