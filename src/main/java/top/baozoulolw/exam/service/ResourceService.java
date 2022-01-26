package top.baozoulolw.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Resource;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    Result<List<Resource>> getResById(Long id,String platform);

    Result addResource(Resource resource);

    Result updateResource(Resource resource);

    Result<List<Resource>> getAllResource(String platform);

    Result delResource(Long id);
}
