package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.QuestionDao;
import top.baozoulolw.exam.entity.PaperQuestion;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.entity.QuestionGroup;
import top.baozoulolw.exam.service.QuestionGroupService;
import top.baozoulolw.exam.service.QuestionService;
import top.baozoulolw.exam.vo.QuestionParamVO;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;

    @Resource
    private PaperQuestionImpl paperQuestion;

    @Resource
    private QuestionGroupService questionGroupService;

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
        Question question = questionDao.selectById(id);
        String groupName = questionGroupService.getById(question.getGroupId()).getGroupName();
        question.setGroupName(groupName);
        return Result.success(question);
    }

    @Override
    public Result delQuestionById(Long id) {
        QueryWrapper<PaperQuestion> wrapper = new QueryWrapper<>();
        wrapper.eq("question_id",id);
        Page<PaperQuestion> page = paperQuestion.page(new Page<>(1, 1), wrapper);
        if(page.getTotal() > 0){
            return Result.fail("当前题目已有试卷使用，无法删除！");
        }
        questionDao.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<List<QuestionGroup>> getGroupList() {
        return Result.success(questionDao.getGroupList());
    }

    @Override
    public Result addGroup(String groupName) {
        QueryWrapper<QuestionGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",groupName);
        QuestionGroup one = questionGroupService.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(one)){
            return Result.fail("分组名重复");
        }
        questionGroupService.save(new QuestionGroup(groupName));
        return Result.success();
    }

    @Override
    public Result delGroup(Long id) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",id);
        Page<Question> questionPage = questionDao.selectPage(new Page<>(1, 1), wrapper);
        if(questionPage.getRecords().size() > 0){
            return Result.fail("当前分组下有试题，无法删除");
        }
        questionGroupService.removeById(id);
        return Result.success();
    }

    @Override
    public Result transGroup(Long from, Long to) {
        UpdateWrapper<Question> wrapper = new UpdateWrapper<>();
        wrapper.eq("group_id",from).set("group_id",to);
        return Result.success();
    }
}
