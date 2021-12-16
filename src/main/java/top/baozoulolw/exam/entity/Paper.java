package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_paper")
public class Paper extends IdEntity implements Serializable {

    @TableField(value = "hard")
    private Integer hard;

    @TableField(value = "content")
    private String content;

    @TableField(value = "total_score")
    private Integer totalScore;

    @TableField(value = "paperName")
    private String paperName;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "drawer")
    private Long drawer;

    @TableField(exist = false)
    private String changeUser;

    @TableField(exist = false)
    private String drawerUser;

    @TableField(exist = false)
    private String teacherGender;
}
