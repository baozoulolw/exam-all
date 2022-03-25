package top.baozoulolw.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import top.baozoulolw.exam.common.Result;
import top.baozoulolw.exam.common.page.PageResult;
import top.baozoulolw.exam.common.page.PageSearch;
import top.baozoulolw.exam.entity.Exam;
import top.baozoulolw.exam.entity.ExamRecord;
import top.baozoulolw.exam.vo.ExamListParamVO;
import top.baozoulolw.exam.vo.MarkingListParamVO;
import top.baozoulolw.exam.vo.RecordListParamVO;

import java.util.List;
import java.util.Map;

@Service
public interface ExamService extends IService<Exam> {

    /**
     * 新增考试
     * @param exam 考试信息
     * @param joinClass 参考分组
     * @return
     */
    Result arrangeExam(Exam exam, List<Long> joinClass);

    /**
     * 查询考试列表
     * @param param 条件参数
     * @return
     */
    Result<PageResult> examList(PageSearch<ExamListParamVO> param);

    /**
     * 验证考试名是否重复
     * @param examName 待验证考试名
     * @return
     */
    Result checkExamName(String examName);

    /**
     * 编辑考试
     * @param exam
     * @return
     */
    Result editExam(Exam exam, List<Long> userGroupIds);

    /**
     * 获取当前用户参与的考试
     * @return
     */
    Result<Map<String, List<Exam>>> getExamById(Integer specifyTime);

    /**
     * 参加考试，获取考试内容
     * @return
     */
    Result<Exam> joinExam(Long examId);

    /**
     * 提交试卷
     * @return
     */
    Result submitExam(ExamRecord examRecord);

    Result<PageResult> markingExam(PageSearch<MarkingListParamVO> param);

    Result<PageResult> recordList(PageSearch<RecordListParamVO> param);

    Result<List<Exam>> getExamOptions();

    Result recordMarking(ExamRecord examRecord);
}
