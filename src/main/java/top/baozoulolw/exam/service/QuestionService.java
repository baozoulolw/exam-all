package top.baozoulolw.exam.service;

import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.vo.QuestionParamVO;

public interface QuestionService {
    Result insertQuestion(Question question);

    Result<PageResult> pageQueryQuestion(PageSearch<QuestionParamVO> param);

    Result editQuestion(Question question);
}
