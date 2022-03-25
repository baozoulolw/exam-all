package top.baozoulolw.exam.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.dao.ExamDao;
import top.baozoulolw.exam.dao.ExamPlanDao;
import top.baozoulolw.exam.dao.PaperDao;
import top.baozoulolw.exam.dao.UserDao;
import top.baozoulolw.exam.entity.*;
import top.baozoulolw.exam.service.ExamPlanService;
import top.baozoulolw.exam.service.ExamRecordService;
import top.baozoulolw.exam.service.ExamService;
import top.baozoulolw.exam.service.PaperService;
import top.baozoulolw.exam.utils.UserUtils;
import top.baozoulolw.exam.vo.ExamListParamVO;
import top.baozoulolw.exam.vo.MarkingListParamVO;
import top.baozoulolw.exam.vo.QueryExamVO;
import top.baozoulolw.exam.vo.RecordListParamVO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamDao, Exam> implements ExamService {

    @Resource
    private ExamPlanService examPlanService;

    @Resource
    private ExamPlanDao examPlanDao;

    @Resource
    private ExamRecordService examRecordService;

    @Resource
    private ExamDao examDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PaperDao paperDao;

    /**
     * 新增考试
     *
     * @param exam      考试信息
     * @param joinClass 参考分组
     * @return
     */
    @Override
    public Result arrangeExam(Exam exam, List<Long> joinClass) {
        Long id = IdWorker.getId();
        exam.setId(id);
        this.save(exam);
        List<ExamPlan> collect = joinClass.stream().map(i -> {
            ExamPlan examPlan = new ExamPlan();
            examPlan.setExamId(id);
            examPlan.setJoinGroupId(i);
            return examPlan;
        }).collect(Collectors.toList());
        examPlanService.saveBatch(collect);
        return Result.success();
    }

    /**
     * 查询考试列表
     *
     * @param param 条件参数
     * @return
     */
    @Override
    public Result<PageResult> examList(PageSearch<ExamListParamVO> param) {
        Page<Exam> page = new Page<>(param.getPageNumber(), param.getPageSize());
        param.getParam().setNowTime(new Date());
        IPage<Exam> examList = examDao.getExamList(page, param.getParam());
        return Result.success(new PageResult(examList));
    }

    /**
     * 验证考试名是否重复
     *
     * @param examName 待验证考试名
     * @return
     */
    @Override
    public Result checkExamName(String examName) {
        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_name", examName);
        Page<Exam> examPage = examDao.selectPage(new Page<>(1, 1), wrapper);
        if (examPage.getRecords().size() > 0) {
            return Result.fail("当前考试名已存在");
        }
        return Result.success();
    }

    /**
     * 编辑考试
     *
     * @param exam
     * @param userGroupIds
     * @return
     */
    @Override
    public Result editExam(Exam exam, List<Long> userGroupIds) {
        QueryWrapper<ExamPlan> examWrapper = new QueryWrapper<>();
        examWrapper.select("join_group_id").eq("exam_id",exam.getId());
        List<Object> objects = examPlanDao.selectObjs(examWrapper);
        List<Long> plans =  objects.stream().map(i -> (Long)i).collect(Collectors.toList());
        List<Long> dels = plans.stream().filter(i -> userGroupIds.stream().noneMatch(ui -> ui.equals(i))).collect(Collectors.toList());
        List<Long> adds = userGroupIds.stream().filter(i -> plans.stream().noneMatch(pi -> pi.equals(i))).collect(Collectors.toList());
        if(dels.size() >0) {
            LambdaQueryWrapper<ExamPlan> delWrapper = Wrappers.lambdaQuery();
            delWrapper.eq(ExamPlan::getExamId, exam.getId()).in(ExamPlan::getJoinGroupId, dels);
            int delete = examPlanDao.delete(delWrapper);
        }
        if(adds.size() > 0) {
            List<ExamPlan> collect = adds.stream().map(i -> {
                ExamPlan examPlan = new ExamPlan();
                examPlan.setExamId(exam.getId());
                examPlan.setJoinGroupId(i);
                return examPlan;
            }).collect(Collectors.toList());
            examPlanService.saveBatch(collect);
        }
        this.updateById(exam);
        return Result.success();
    }

    /**
     * 获取当前用户参与的考试
     *
     * @return
     */
    @Override
    public Result<Map<String, List<Exam>>> getExamById(Integer specifyTime) {
        Long userId = UserUtils.getUserId();
        User user = userDao.selectById(userId);
        Date now = new Date();
        Map<String, List<Exam>> result = new HashMap<>(3);
        QueryExamVO queryExamVO = new QueryExamVO(user.getGroupId(), userId , now, 0, 0);
        if(specifyTime == null || specifyTime != 1){
            // 未定时待完成
            queryExamVO.setStatus(1);
            List<Exam> timingIncomplete = examDao.getExamByGroup(queryExamVO);
            result.put("timingIncomplete",timingIncomplete);
        }
        if(specifyTime == null || specifyTime != 0){
            // 定时未开始
            queryExamVO.setStatus(0);
            queryExamVO.setSpecifyTime(1);
            List<Exam> timingNotStarted = examDao.getExamByGroup(queryExamVO);
            // 定时进行中
            queryExamVO.setStatus(1);
            List<Exam> onSchedule = examDao.getExamByGroup(queryExamVO);
            result.put("onSchedule",onSchedule);
            result.put("timingNotStarted",timingNotStarted);
        }
        return Result.success(result);
    }

    /**
     * 参加考试，获取考试内容
     *
     * @param examId
     * @return
     */
    @Override
    public Result<Exam> joinExam(Long examId) {
        Long userId = UserUtils.getUserId();
        User user = userDao.selectById(userId);
        LambdaQueryWrapper<ExamRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ExamRecord::getExamId,examId).eq(ExamRecord::getUserId,userId)
                .last("limit 1");
        ExamRecord one = examRecordService.getOne(wrapper);
        if(one != null){
            return Result.fail("已参加该考试，不能重复考试");
        }
        Exam exam = examDao.selectById(examId);
        Paper paper = paperDao.getPaperById(exam.getPaperId());
        exam.setPaper(paper);
        return Result.success(exam);
    }

    /**
     * 提交试卷
     *
     * @param examRecord
     * @return
     */
    @Override
    public Result submitExam(ExamRecord examRecord) {
        examRecord.setUserId(UserUtils.getUserId());
        boolean save = examRecordService.save(examRecord);
        return Result.success();
    }

    @Override
    public Result<PageResult> markingExam(PageSearch<MarkingListParamVO> param) {
        Page<Exam> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<Exam> markingList = examDao.getMarkingList(page, param.getParam());
        return  Result.success(new PageResult(markingList));
    }

    @Override
    public Result<PageResult> recordList(PageSearch<RecordListParamVO> param) {
        Page<Exam> page = new Page<>(param.getPageNumber(), param.getPageSize());
        IPage<ExamRecord> recordList = examDao.getRecordList(page, param.getParam());
        return  Result.success(new PageResult(recordList));
    }

    @Override
    public Result<List<Exam>> getExamOptions() {
        List<Exam> exams = examDao.selectList(new QueryWrapper<>());
        return Result.success(exams);
    }

    @Override
    public Result recordMarking(ExamRecord examRecord) {
        examRecordService.updateById(examRecord);
        return Result.success();
    }
}
