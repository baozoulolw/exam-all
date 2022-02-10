package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 用户角色绑定实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 10:03
 */
@Data
@TableName(value = "tb_user_role")
public class UserRole extends IdEntity implements Serializable {

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;
}
