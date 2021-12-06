package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@TableName(value = "tb_grade")
public class Grade extends IdEntity implements Serializable {

    @TableField(value = "grade_name")
    private String gradeName;

    @TableField(exist = false)
    private String changeUser;
}
