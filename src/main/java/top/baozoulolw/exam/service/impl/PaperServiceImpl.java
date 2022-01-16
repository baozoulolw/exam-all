package top.baozoulolw.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.PaperDao;
import top.baozoulolw.exam.dao.QuestionDao;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.Paper;
import top.baozoulolw.exam.entity.PaperQuestion;
import top.baozoulolw.exam.entity.Question;
import top.baozoulolw.exam.service.PaperService;
import top.baozoulolw.exam.utils.UserUtils;
import top.baozoulolw.exam.vo.ChangeSortPaperParamVO;
import top.baozoulolw.exam.vo.PaperParamVO;
import top.baozoulolw.exam.vo.QuestionParamVO;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperDao, Paper> implements  PaperService {

    @Resource
    private PaperDao paperDao;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PaperQuestionImpl paperQuestionImpl;

    @Override
    public Result<PageResult> queryPage(PageSearch<PaperParamVO> param) {
        Page<Paper> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<Paper> result = paperDao.getPaperList(page, param.getParam());
        return Result.success(new PageResult(result));
    }

    @Override
    public Result<PageResult> addPaper(Paper paper) {
        paper.setDrawer(UserUtils.getUserId());
        paperDao.insert(paper);
        return Result.success();
    }

    @Override
    public Result<Paper> getPaperById(Long id) {
//        Paper paper = paperDao.selectById(id);
//        User drawer = userDao.selectById(paper.getDrawer());
//        User changeUser = userDao.selectById(paper.getOperUser());
//        paper.setDrawerUser(drawer.getRealName());
//        paper.setTeacherGender(drawer.getGender());
//        paper.setChangeUser(changeUser.getRealName());
//        paper.setChangeUserInfo(changeUser);
        Paper paper = paperDao.getPaperById(id);
        return Result.success(paper);
    }

    @Override
    public Result updatePaper(Paper paper) {
        int i = paperDao.updateById(paper);
        if(i == 0){
            return Result.fail("更新失败");
        }
        return Result.success();
    }

    @Override
    public Result delPaper(Long id) {
        int i = paperDao.deleteById(id);
        if(i == 0){
            return Result.fail("删除失败");
        }
        return Result.success();
    }

    @Override
    public Result<PageResult> getSelectQuestion(PageSearch<QuestionParamVO> param) {
        Page<Question> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<Question> result = questionDao.getQuestionList(page, param.getParam());
        PageResult pageResult = new PageResult(result);
        return Result.success(pageResult);
    }

    @Override
    public Result addQuestion(List<PaperQuestion> param) {
        paperQuestionImpl.saveBatch(param,param.size());
        return Result.success();
    }

    @Override
    public Result changeSort(ChangeSortPaperParamVO param) {
        List<Long> delIds = param.getDelIds();
        List<PaperQuestion> sorts = param.getSorts();
        if(delIds.size() > 0){
            paperQuestionImpl.removeByIds(delIds);
        }
        if(sorts.size() > 0){
            paperQuestionImpl.saveOrUpdateBatch(sorts);
        }
        return Result.success();
    }

    @Override
    public Result changeScore(PaperQuestion param) {
        Integer score = param.getScore();
        if(score < 0){
            return Result.fail("输入成绩有误");
        }
        paperQuestionImpl.saveOrUpdate(param);
        return Result.success();
    }
}
