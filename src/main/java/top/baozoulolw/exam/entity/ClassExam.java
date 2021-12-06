package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_class")
public class ClassExam extends IdEntity implements Serializable {

    @TableField(value = "class_name")
    private String className;

    @TableField(value = "head_teacher")
    private Long headTeacher;

    @TableField(value = "grade_id")
    private Long gradeId;

    @TableField(exist = false)
    private String changeUser;

    @TableField(exist = false)
    private User teacher;

    @TableField(exist = false)
    private Grade grade;
}
