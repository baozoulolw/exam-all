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

    /**
     * 登录用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码,需加密存储
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 状态
     */
    @TableField(value = "status")
    private int status;


    @TableField(value = "class_id")
    private Long classId;

    /**
     * 头像地址(阿里云oss对象存储)
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 分组id
     */
    @TableField(value = "group_id")
    private Long groupId;


    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    @TableField(exist = false)
    private String changeUser;

    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private ClassExam classExam;

    /**
     * 拼接好的头像地址
     */
    @TableField(exist = false)
    private String avatarUrl;

    /**
     * 角色id
     */
    @TableField(exist = false)
    private List<Long> roleIds;



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
