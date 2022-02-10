package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;

/**
 * 组织实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 15:09
 */
@TableName(value = "tb_organization")
@Data
public class Organization extends IdEntity implements Serializable {

    /**
     * 组织名称
     */
    @TableField(value = "organization_name")
    private String organizationName;

    /**
     * 父级组织
     */
    @TableField(value = "parent")
    private Long parent;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}
