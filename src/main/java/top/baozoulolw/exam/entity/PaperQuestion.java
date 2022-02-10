package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 试题试卷关联实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 15:03
 */
@Data
@TableName(value = "tb_question_paper")
public class PaperQuestion extends IdEntity implements Serializable {

    /**
     * 试题id
     */
    @TableField(value = "question_id")
    private Long questionId;

    /**
     * 试卷id
     */
    @TableField(value = "paper_id")
    private Long paperId;

    /**
     * 分值
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 试题序号
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 试题信息
     */
    @TableField(exist = false)
    private Question question;
}
