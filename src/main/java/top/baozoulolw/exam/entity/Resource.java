package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.List;

@Data
@TableName(value = "tb_resource")
public class Resource extends IdEntity implements Serializable {

    @TableField(value = "resource_name")
    private String resourceName;

    @TableField(value = "type")
    private String type;

    @TableField(value = "weights")
    private int weights;

    @TableField(value = "parent")
    private Long parent;

    @TableField(value = "is_root")
    private int isRoot;

    @TableField(value = "path")
    private String path;

    @TableField(value = "icon")
    private String icon;

    @TableField(exist = false)
    private String changUser;

    @TableField(exist = false)
    private List<Resource> children;
}
