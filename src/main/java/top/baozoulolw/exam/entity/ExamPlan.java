package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_exam_plan")
public class ExamPlan extends IdEntity implements Serializable {

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "exam_id")
    private Long examId;

    @TableField(value = "refer")
    private Integer refer;
}
