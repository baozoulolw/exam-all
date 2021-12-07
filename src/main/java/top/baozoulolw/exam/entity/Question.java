package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_question")
public class Question extends IdEntity implements Serializable {

    @TableField(value = "topic")
    private String topic;

    @TableField(value = "answer")
    private String answer;

    @TableField(value = "options")
    private String options;

    @TableField(value = "type")
    private int type;

    @TableField(value = "hard")
    private String hard;

    @TableField(exist = false)
    private String changeUser;
}
