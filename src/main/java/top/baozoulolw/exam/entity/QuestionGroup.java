package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_question_group")
public class QuestionGroup extends IdEntity implements Serializable {

    @TableField(value = "group_name")
    private String groupName;

    @TableField(exist = false)
    private int questionNumber;
}
