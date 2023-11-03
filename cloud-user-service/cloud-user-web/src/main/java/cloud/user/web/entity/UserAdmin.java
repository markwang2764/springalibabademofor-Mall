package cloud.user.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserAdmin)实体类
 *
 * @author makejava
 * @since 2023-09-06 12:35:54
 */
@Data
@TableName(value = "tb_mall_admin_user")
public class UserAdmin implements Serializable {
    private static final long serialVersionUID = -88800142070642125L;
    /**
     * 管理员id
     */
    private Long adminUserId;
    /**
     * 管理员登录名称
     */
    private String loginUserName;
    /**
     * 管理员登录密码
     */
    private String loginPassword;
    /**
     * 管理员显示昵称
     */
    private String nickName;
    /**
     * 是否锁定 0未锁定 1已锁定无法登录
     */
    private Integer locked;
}

