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
    @Override
    public Result<List<Resource>> getResById(Long id) {
        return null;
    }
}
