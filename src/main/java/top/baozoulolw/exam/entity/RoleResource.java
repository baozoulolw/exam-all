package top.baozoulolw.exam.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 角色资源绑定记录实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 10:02
 */
@TableName(value = "tb_role_resource")
@Data
public class RoleResource extends IdEntity implements Serializable {

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 资源id
     */
    @TableField(value = "resource_id")
    private Long resourceId;
}
