package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.Exam;
import top.baozoulolw.exam.entity.ExamRecord;
import top.baozoulolw.exam.vo.ExamListParamVO;
import top.baozoulolw.exam.vo.MarkingListParamVO;
import top.baozoulolw.exam.vo.QueryExamVO;
import top.baozoulolw.exam.vo.RecordListParamVO;

import java.util.List;

@Repository
public interface ExamDao extends BaseMapper<Exam> {

    /**
     * 分页条件查询出examList
     * @param page 分页参数
     * @param param 查询条件参数
     * @return 查询结果
     */
    IPage<Exam> getExamList(Page<Exam> page, @Param("param")ExamListParamVO param);

    List<Exam> getExamByGroup(@Param("param")QueryExamVO queryExamVO);

    IPage<Exam> getMarkingList(Page<Exam> page, @Param("param")MarkingListParamVO param);

    IPage<ExamRecord> getRecordList(Page<Exam> page, @Param("param")RecordListParamVO param);
}
