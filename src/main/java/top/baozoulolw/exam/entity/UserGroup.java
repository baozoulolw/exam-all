package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.baozoulolw.exam.common.publicfields.IdEntity;


/**
 * 成员分类实体类
 *
 * @author baozoulolw
 * @version 1.0
 * @date 2022-01-29 14:50
 */
@Data
@TableName(value = "tb_user_group")
public class UserGroup extends IdEntity {

    /**
     * 分类名
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 分类下的人员数量
     */
    @TableField(exist = false)
    private int userNumber;
}
