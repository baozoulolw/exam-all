package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.vo.QuestionParamVO;

import java.util.List;

public interface QuestionService {
    Result insertQuestion(Question question);

    Result<PageResult> pageQueryQuestion(PageSearch<QuestionParamVO> param);

    Result editQuestion(Question question);

    Result<Question> getQuestionById(Long id);

    Result delQuestionById(Long id);

    Result<List<QuestionGroup>> getGroupList();

    Result addGroup(String groupName);

    Result delGroup(Long id);

    Result transGroup(Long from, Long to);

    Result editGroup(QuestionGroup questionGroup);
}
