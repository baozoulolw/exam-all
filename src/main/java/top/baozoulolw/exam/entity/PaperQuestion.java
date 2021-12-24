package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_question_paper")
public class PaperQuestion extends IdEntity implements Serializable {

    @TableField(value = "question_id")
    private Long questionId;

    @TableField(value = "paper_id")
    private Long paperId;

    @TableField(value = "score")
    private Integer score;

    @TableField(value = "index")
    private Integer index;

    @TableField(exist = false)
    private Question question;
}
