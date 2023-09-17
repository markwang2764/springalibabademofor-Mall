package recommend.service.web.entity;

import lombok.Data;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: user admin
 * @author: Mr.markWang 2764
 * @create: 2023-09-17 22:50
 **/
@Data
public class UserAdmin {
    private Long adminUserId;
    private String loginUserName;
    private String loginPassword;
    private String nickName;
    private Byte locked;
}
