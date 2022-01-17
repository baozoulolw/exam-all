package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_exam_record")
public class ExamRecord extends IdEntity implements Serializable {

    @TableField(value = "userId")
    private Long userId;

    @TableField(value = "examId")
    private Long examId;

    @TableField(value = "score")
    private Integer score;

    @TableField(value = "finish")
    private Integer finish;

    @TableField(value = "answer")
    private String answer;

    @TableField(value = "begin_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Data beginTime;

    @TableField(value = "end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Data endTime;
}
