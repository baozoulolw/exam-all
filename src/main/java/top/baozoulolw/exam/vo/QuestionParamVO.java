package top.baozoulolw.exam.vo;

import lombok.Data;

@Data
public class QuestionParamVO {
    private Integer hard;
    private Integer type;
    private String keyword;
    private Long groupId;
    private Long createUser;
    private Long selectPaperId;
}
