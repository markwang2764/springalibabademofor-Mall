package goods.service.web.entity;

import lombok.Data;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: userEntity
 * @author: Mr.markWang 2764
 * @create: 2023-09-11 00:14
 **/
@Data
public class UserAdmin {
    private Long adminUserId;
    private String loginUserName;
    private String loginPassword;
    private String nickName;
    private Byte locked;
}
