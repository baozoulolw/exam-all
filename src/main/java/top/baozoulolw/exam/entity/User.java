package top.baozoulolw.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.baozoulolw.exam.common.publicfields.IdEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体类
 *
 * @author Baozoulolw
 * @version 1.0
 * @date 2021-06-09 10:43
 */
@Data
@TableName(value = "tb_user")
public class User extends IdEntity implements UserDetails, Serializable {
    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "email")
    private String email;

    @TableField(value = "address")
    private String address;

    @TableField(value = "status")
    private int status;

    @TableField(value = "class_id")
    private Long classId;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "real_name")
    private String realName;

    @TableField(value = "gender")
    private String gender;

    @TableField(exist = false)
    private String changeUser;

    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private ClassExam classExam;



    /**
     * 账户是否未过期,过期无法验证，在springSecurity 验证中自动调用
     */
    @TableField(exist = false)
    private boolean isAccountNonExpired = true;

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证，在springSecurity 验证中自动调用
     */
    @TableField(exist = false)
    private boolean isAccountNonLocked = true;

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证，在springSecurity 验证中自动调用
     */
    @TableField(exist = false)
    private boolean isCredentialsNonExpired = true;

    /**
     * 是否可用 ,禁用的用户不能身份验证，在springSecurity 验证中自动调用
     */
    @TableField(exist = false)
    private boolean isEnabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
