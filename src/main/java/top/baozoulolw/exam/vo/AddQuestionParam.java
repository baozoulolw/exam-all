package top.baozoulolw.exam.vo;

import lombok.Data;

@Data
public class AddQuestionParam {
    private Long paperId;
    private Integer score;
    private Long questionId;
    private Integer index;
}
