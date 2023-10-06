package com.example.springcloudalibabauserweb.entity;

import java.io.Serializable;

/**
 * (UserAdmin)实体类
 *
 * @author makejava
 * @since 2023-09-06 12:35:54
 */
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


    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

}

