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
public class UpdateAdminNameParam {
    @NotEmpty(message = "loginUserName 不能为空")
    private String loginUserName;

    @NotEmpty(message = "nickName 不能为空")
    private String nickName;
}
