package top.baozoulolw.exam.vo;

import lombok.Data;

@Data
public class RecordListParamVO {
    private Long userId;
    private Long examId;
    private String keyword;
    private Long groupId;
    private Integer marking;
}
