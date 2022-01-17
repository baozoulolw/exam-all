package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "tb_exam")
public class Exam extends IdEntity implements Serializable {

    @TableField(value = "paper_id")
    private Long paperId;

    @TableField(value = "specify_time")
    private Integer specifyTime;

    @TableField(value = "begin_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;

    @TableField(value = "end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Data endTime;

    @TableField(value = "specify_student")
    private Integer specifyStudent;

    @TableField(value = "duration")
    private Integer duration;

    @TableField(value = "attention")
    private String attention;
}
