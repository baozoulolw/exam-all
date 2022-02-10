package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 试题分类实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 14:50
 */
@Data
@TableName(value = "tb_question_group")
public class QuestionGroup extends IdEntity implements Serializable {

    /**
     * 分类名
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 分类下的试题数量
     */
    @TableField(exist = false)
    private int questionNumber;
}
