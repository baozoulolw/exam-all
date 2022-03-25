package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试记录试题
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 15:14
 */
@Data
@TableName(value = "tb_exam_record")
public class ExamRecord extends IdEntity implements Serializable {

    /**
     * 参考人id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 考试id
     */
    @TableField(value = "exam_id")
    private Long examId;

    /**
     * 分数
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 是否完成交卷 0:否 1:是
     */
    @TableField(value = "finish")
    private Integer finish;

    /**
     * 答案
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 是否需要阅卷0否，1是
     */
    @TableField(value = "marking")
    private Integer marking;

    /**
     * 开始考试时间
     */
    @TableField(value = "begin_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;

    /**
     * 交卷时间
     */
    @TableField(value = "end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    @TableField(exist = false)
    private Exam exam;

    @TableField(exist = false)
    private String groupName;

    @TableField(exist = false)
    private String username;
}
