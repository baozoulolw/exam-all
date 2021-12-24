package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.entity.User;
import top.baozoulolw.exam.vo.PaperParamVO;
import top.baozoulolw.exam.vo.UserLIstParamVO;

@Repository
public interface PaperDao extends BaseMapper<Paper> {
    /**
     * 分页条件查询出试卷
     * @param page 分页参数
     * @param param 查询条件参数
     * @return 查询结果
     */
    IPage<Paper> getPaperList(Page<Paper> page, @Param("param") PaperParamVO param);

    Paper getPaperById(@Param("param")Long id);
}
