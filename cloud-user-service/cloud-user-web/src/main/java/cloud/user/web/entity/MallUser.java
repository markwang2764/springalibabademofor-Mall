package cloud.user.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Date;
import java.io.Serializable;

/**
 * (MallUser)实体类
 *
 * @author makejava
 * @since 2023-10-19 21:25:04
 */
@Data
public class MallUser implements Serializable {
    private static final long serialVersionUID = -92764718783323538L;
    /**
     * 用户主键id
     */
    private Long userId;
    private String passwordMd5;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 登陆名称(默认为手机号)
     */
    private String loginName;

    /**
     * 个性签名
     */
    private String introduceSign;
    /**
     * 注销标识字段(0-正常 1-已注销)
     */
    private Integer isDeleted;
    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     */
    private Integer lockedFlag;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}

