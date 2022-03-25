package top.baozoulolw.exam.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class QueryExamVO {

    private Long groupId;
    private Long userId;
    private Date now;
    /**
     * 0:不定时 1：定时
     */
    private Integer specifyTime;
    /**
     * 0：待完成 1：进行中
     */
    private Integer status;
}
