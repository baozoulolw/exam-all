package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 考试计划实体类
 */
@Data
@TableName(value = "tb_exam_plan")
public class ExamPlan extends IdEntity implements Serializable {

    /**
     * 参考分组id
     */
    @TableField(value = "join_group_id")
    private Long joinGroupId;

    /**
     * 考试id
     */
    @TableField(value = "exam_id")
    private Long examId;

    /**
     * 是否参考 0:未参考 1:参考
     */
    @TableField(value = "refer")
    private Integer refer;
}
