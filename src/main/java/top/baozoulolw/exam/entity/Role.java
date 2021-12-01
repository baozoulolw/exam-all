package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

@Data
@TableName(value = "tb_role")
public class Role extends IdEntity implements Serializable {

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否为默认角色 0：否 1：是
     */
    @TableField(value = "is_default")
    private String isDefault;

    /**
     * 最后操作人名
     */
    @TableField(exist = false)
    private String changeUserName;
}
