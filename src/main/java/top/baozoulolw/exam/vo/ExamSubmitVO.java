package top.baozoulolw.exam.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class ExamSubmitVO {

    /**
     * 考试id
     */
    private Long examId;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 是否完成交卷 0:否 1:是
     */
    private Integer finish;

    /**
     * 答案
     */
    private String answer;


    /**
     * 开始考试时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;

    /**
     * 交卷时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
}
