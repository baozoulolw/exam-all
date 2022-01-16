package top.baozoulolw.exam.vo;

import lombok.Data;
import top.baozoulolw.exam.entity.PaperQuestion;

import java.util.List;

@Data
public class ChangeSortPaperParamVO {
    private List<Long> delIds;
    private List<PaperQuestion> sorts;

}
