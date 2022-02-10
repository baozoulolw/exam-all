package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 15:06
 */
@Data
@TableName(value = "tb_paper")
public class Paper extends IdEntity implements Serializable {

    /**
     * 难度 0：简单 1：中等 2：困难
     */
    @TableField(value = "hard")
    private Integer hard;

    /**
     * 试卷内容（暂时没使用此属性）
     */
    @TableField(value = "content")
    private String content;

    /**
     * 总分
     */
    @TableField(exist = false)
    private Integer totalScore;

    /**
     * 试卷名
     */
    @TableField(value = "paper_name")
    private String paperName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 出卷人id
     */
    @TableField(value = "drawer")
    private Long drawer;

    /**
     * 最后操作人姓名
     */
    @TableField(exist = false)
    private String changeUser;

    /**
     * 出卷人名字
     */
    @TableField(exist = false)
    private String drawerUser;

    @TableField(exist = false)
    private String teacherGender;

    /**
     * 出卷人信息
     */
    @TableField(exist = false)
    private User drawerUserInfo;

    /**
     * 最后操作人信息
     */
    @TableField(exist = false)
    private User changeUserInfo;

    /**
     * 此试卷下的试题列表
     */
    @TableField(exist = false)
    private List<PaperQuestion> questions;
}
