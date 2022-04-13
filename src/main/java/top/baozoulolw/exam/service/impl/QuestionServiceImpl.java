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
import top.baozoulolw.exam.utils.UserUtils;
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
        question.setCreateUser(UserUtils.getUserId());
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
    public Result<List<QuestionGroup>> getGroupList(int flag) {
        Long userId = null;
        if(flag == 1){
            userId = UserUtils.getUserId();
        }
        List<QuestionGroup> groupList = questionDao.getGroupList(userId,UserUtils.getUserId());
        groupList.forEach(group -> {
            group.setQuestionNumber(group.getChildren().stream().reduce(0,(pre,i )-> pre + i.getQuestionNumber(),Integer::sum));
        });
        return Result.success(groupList);
    }

    @Override
    public Result addGroup(String groupName,Long parent) {
        QueryWrapper<QuestionGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",groupName).eq("parent",parent);
        QuestionGroup one = questionGroupService.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(one)){
            return Result.fail("当前层级分类名字存在重复");
        }
        QuestionGroup questionGroup = new QuestionGroup();
        questionGroup.setGroupName(groupName);
        questionGroup.setParent(parent);
        questionGroupService.save(questionGroup);
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
        questionDao.update(null,wrapper);
        return Result.success();
    }

    @Override
    public Result editGroup(QuestionGroup questionGroup) {
        QueryWrapper<QuestionGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",questionGroup.getGroupName());
        QuestionGroup one = questionGroupService.getOne(wrapper);
        if(ObjectUtils.isNotEmpty(one)){
            return Result.fail("分组名重复");
        }
        questionGroupService.updateById(questionGroup);
        return Result.success();
    }

    @Override
    public Result<List<QuestionGroup>> getCourseAll() {
        List<QuestionGroup> allCourse = questionDao.getAllGroupList();
        return Result.success(allCourse);
    }
}
