package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 资源实体
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 10:03
 */
@Data
@TableName(value = "tb_resource")
public class Resource extends IdEntity implements Serializable {

    /**
     * 资源名称
     */
    @TableField(value = "resource_name")
    private String resourceName;

    /**
     * 资源类型 menu：菜单 （暂时只支持菜单类型）
     */
    @TableField(value = "type")
    private String type;

    /**
     * 权重，就是排列顺序
     */
    @TableField(value = "weights")
    private int weights;

    /**
     * 父级资源id （暂时只支持两级）
     */
    @TableField(value = "parent")
    private Long parent;

    /**
     * 资源备注
     */
    @TableField(value = "note")
    private String note;

    /**
     * 是否为根资源 0：否 1：是
     */
    @TableField(value = "is_root")
    private int isRoot;

    /**
     * 资源点击跳转地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 资源图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 平台 student：学生平台 ；teacher：教师平台；manage：管理平台
     */
    @TableField(value = "platform")
    private String platform;

    @TableField(exist = false)
    private String changUser;

    /**
     * 子资源list
     */
    @TableField(exist = false)
    private List<Resource> children;
}
