package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 试题试题类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 15:00
 */
@Data
@TableName(value = "tb_question")
public class Question extends IdEntity implements Serializable {

    /**
     * 题目
     */
    @TableField(value = "topic")
    private String topic;

    /**
     * 答案
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 选项
     */
    @TableField(value = "options")
    private String options;

    /**
     * 试题类型 0：单选题 1：多选题 2：判断题  3：填空题
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 难度 0：简单 1：中等 2：困难
     */
    @TableField(value = "hard")
    private Integer hard;

    /**
     * 所属分类id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 最后操作人姓名
     */
    @TableField(exist = false)
    private String changeUser;

    /**
     * 所属分类名
     */
    @TableField(exist = false)
    private String groupName;

}
