package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 考试实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 15:10
 */
@Data
@TableName(value = "tb_exam")
public class Exam extends IdEntity implements Serializable {

    /**
     * 考试所用试卷id
     */
    @TableField(value = "paper_id")
    private Long paperId;

    /**
     * 考试名称
     */
    @TableField(value = "exam_name")
    private String examName;

    /**
     * 通过分数
     */
    @TableField(value = "pass_score")
    private Integer passScore;

    /**
     * 是否指定时间 0：否 1：是
     */
    @TableField(value = "specify_time")
    private Integer specifyTime;

    /**
     * 开始考试时间
     */
    @TableField(value = "begin_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;

    /**
     * 结束考试时间
     */
    @TableField(value = "end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * 是否指定参考学生 0：否 1：是
     */
    @TableField(value = "specify_student")
    private Integer specifyStudent;

    /**
     * 考试时长（单位：分钟）
     */
    @TableField(value = "duration")
    private Integer duration;

    /**
     * 考试注意事项
     */
    @TableField(value = "attention")
    private String attention;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Long createUser;


    /**
     * 参考学生分类列表
     */
    @TableField(exist = false)
    private List<UserGroup> userGroupList;

    /**
     * 所用试卷
     */
    @TableField(exist = false)
    private Paper paper;

    /**
     * 参考学生分类id
     */
    @TableField(exist = false)
    private List<Long> userGroupIds;

    /**
     * 最后修改人名字
     */
    @TableField(exist = false)
    private String changeUserName;

    @TableField(exist = false)
    private Integer finish;

    @TableField(exist = false)
    private List<Long> examRecordIds;

    @TableField(exist = false)
    private String groupName;

    @TableField(exist = false)
    private ExamRecord examRecord;

}
