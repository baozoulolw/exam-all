package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.QuestionDao;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.service.QuestionService;
import top.baozoulolw.exam.vo.QuestionParamVO;

import javax.annotation.Resource;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;

    @Override
    public Result insertQuestion(Question question) {
        questionDao.insert(question);
        return Result.success();
    }

    @Override
    public Result<PageResult> pageQueryQuestion(PageSearch<QuestionParamVO> param) {
        Page<Question> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<Question> result = questionDao.getQuestionList(page, param.getParam());
        PageResult pageResult = new PageResult(result);
        return Result.success(pageResult);
    }

    @Override
    public Result editQuestion(Question question) {
        questionDao.updateById(question);
        return Result.success();
    }

    @Override
    public Result<Question> getQuestionById(Long id) {
        return Result.success(questionDao.selectById(id));
    }
}
