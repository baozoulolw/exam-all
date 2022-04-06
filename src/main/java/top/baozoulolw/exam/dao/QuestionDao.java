package top.baozoulolw.exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.vo.QuestionParamVO;

import java.util.List;

@Repository
public interface QuestionDao extends BaseMapper<Question> {

    /**
     * 分页条件查询出userList
     * @param page 分页参数
     * @param question 查询条件参数
     * @return 查询结果
     */
    IPage<Question> getQuestionList(Page<Question> page, @Param("param") QuestionParamVO question);

    List<QuestionGroup> getGroupList(@Param("userId") Long userId);
}
