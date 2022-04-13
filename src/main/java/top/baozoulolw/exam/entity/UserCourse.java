package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

@TableName(value = "tb_user_course")
@Data
public class UserCourse extends IdEntity {

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "course_id")
    private Long courseId;
}
