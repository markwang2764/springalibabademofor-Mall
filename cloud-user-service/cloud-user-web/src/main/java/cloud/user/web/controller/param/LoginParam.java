package cloud.user.web.controller.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: 登录参数
 * @author: Mr.markWang 2764
 * @create: 2023-09-06 14:12
 **/
@Data
public class LoginParam implements Serializable{
    @ApiModelProperty("登录名")
    @NotEmpty(message = "登录名不能为空")
    private String userName;

    @ApiModelProperty("用户密码(需要MD5加密)")
    @NotEmpty(message = "密码不能为空")
    private String passwordMd5;
}
