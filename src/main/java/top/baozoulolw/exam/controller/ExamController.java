package top.baozoulolw.exam.controller;

import org.springframework.web.bind.annotation.*;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Exam;
import top.baozoulolw.exam.entity.ExamRecord;
import top.baozoulolw.exam.service.ExamService;
import top.baozoulolw.exam.vo.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;

/**
 * 考试安排相关借口
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-02-27 18:45
 */
@RestController
@RequestMapping(value = "/exam")
public class ExamController {

    @Resource
    private ExamService examService;

    /**
     * 新增考试
     * @param exam
     * @return
     */
    @PostMapping(value = "/arrange")
    public Result examArrange(@RequestBody Exam exam){
        return examService.arrangeExam(exam,exam.getUserGroupIds());
    }

    /**
     * 编辑考试
     * @param exam
     * @return
     */
    @PostMapping(value = "/edit")
    public Result examEdit(@RequestBody Exam exam){
        return examService.editExam(exam,exam.getUserGroupIds());
    }

    /**
     * 查询考试列表
     * @param param 条件参数
     * @return
     */
    @PostMapping(value = "/list")
    public Result<PageResult> getExamList(@RequestBody PageSearch<ExamListParamVO> param){
        return examService.examList(param);
    }

    /**
     * 验证考试名是否重复
     * @param examName 待验证考试名
     * @return
     */
    @GetMapping(value = "/check/{examName}")
    public Result checkExamName(@PathVariable("examName") String examName){
        return examService.checkExamName(examName);
    }

    /**
     * 获取当前用户参与的考试
     * @return
     */
    @GetMapping(value = "/one")
    public Result<Map<String,List<Exam>>> getExamById(@PathParam("specifyTime") Integer specifyTime){
        return examService.getExamById(specifyTime);
    }

    /**
     * 参加考试，获取考试内容
     * @return
     */
    @GetMapping(value = "/join/{examId}")
    public Result<Exam> joinExam(@PathVariable("examId")Long examId){
        return examService.joinExam(examId);
    }

    /**
     * 提交试卷
     * @return
     */
    @PostMapping(value = "/submit")
    public Result submitExam(@RequestBody ExamRecord examRecord){
        return examService.submitExam(examRecord);
    }

    /**
     * 考试成绩列表(考试记录)
     * @return
     */
    @PostMapping(value = "/record/list")
    public Result<PageResult> recordList(@RequestBody PageSearch<RecordListParamVO> param){
        return examService.recordList(param);
    }

    /**
     * 获取阅卷列表(考试记录)
     * @return
     */
    @PostMapping(value = "/marking/list")
    public Result<PageResult> planList(@RequestBody PageSearch<MarkingListParamVO> param){
        return examService.markingExam(param);
    }

    /**
     * 阅卷
     * @return
     */
    @PostMapping(value = "/marking")
    public Result recordMarking(@RequestBody ExamRecord examRecord){
        return examService.recordMarking(examRecord);
    }

    @GetMapping(value = "/exams")
    public Result<List<Exam>> getExamOptions(){
        return examService.getExamOptions();
    }
}
