package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.List;

@Data
@TableName(value = "tb_paper")
public class Paper extends IdEntity implements Serializable {

    @TableField(value = "hard")
    private Integer hard;

    @TableField(value = "content")
    private String content;

    @TableField(exist = false)
    private Integer totalScore;

    @TableField(value = "paper_name")
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

    @TableField(exist = false)
    private User drawerUserInfo;

    @TableField(exist = false)
    private User changeUserInfo;

    @TableField(exist = false)
    private List<PaperQuestion> questions;
}
