package top.baozoulolw.exam.vo;

import lombok.Data;
import top.baozoulolw.exam.entity.Exam;

import java.util.List;

@Data
public class ExamArrangeParamVO {
    private Exam exam;
    private List<Long> joinClass;
}
