package top.baozoulolw.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.entity.Resource;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    /**
     * 根据用户id获取用户权限对应的权限列表
     * @param id 用户id
     * @param platform 平台
     * @return
     */
    Result<List<Resource>> getResById(Long id,String platform);

    /**
     * 添加资源
     * @param resource
     * @return
     */
    Result addResource(Resource resource);

    /**
     * 更新资源
     * @param resource
     * @return
     */
    Result updateResource(Resource resource);

    /**
     * 根据平台获取平台下所有资源列表
     * @param platform
     * @return
     */
    Result<List<Resource>> getAllResource(String platform);

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    Result delResource(Long id);

    Result checkKey(String key);
}
