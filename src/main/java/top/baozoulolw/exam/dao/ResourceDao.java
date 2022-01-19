package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.Resource;

import java.util.List;

@Repository
public interface ResourceDao extends BaseMapper<Resource> {

    List<Resource> getAllResource();
}
