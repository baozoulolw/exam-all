package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.Resource;

import java.util.List;

@Repository
public interface ResourceDao extends BaseMapper<Resource> {

    List<Resource> getAllResource(@Param("platform") String platform);

    List<Resource> getResourceByUserId(@Param("param") Long id,@Param("platform") String platform);

    List<Long> hasResource(@Param("id") Long id, @Param("platform")String platform);

    List<Long> checkKeys(@Param("id") Long id, @Param("platform")String platform);
}
