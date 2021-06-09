package top.baozoulolw.webchat.common.publicfields;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * // 带id实体类
 *
 * @author Baozoulolw
 * @version 1.0
 * @date 2021-01-18 15:53
 */
@Data
public class IdEntity extends BaseEntity {

    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;
}
