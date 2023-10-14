package cloud.user.web.controller.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-12 06:46
 **/
@Data
public class UpdateAdminPasswordParam {
    @NotEmpty(message = "originalPassword 不能为空")
    private String originalPassword;

    @NotEmpty(message = "newPassword 不能为空")
    private String newPassword;
}
