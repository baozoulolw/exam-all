package top.baozoulolw.exam.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ExamListParamVO {

    /**
     * 0：未开始  1：进行中  2：已结束
     */
    private Integer condition;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 开始考试时间
     */
    private Date beginTime;

    /**
     * 结束考试时间
     */
    private Date endTime;

    /**
     * 当前时间
     */
    private Date nowTime;

}
