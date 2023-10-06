package cloud.common.springcloud.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: admin token
 * @author: Mr.markWang 2764
 * @create: 2023-09-07 22:45
 **/
@Data
public class AdminUserToken implements Serializable {
    private Long adminUserId;
    private String token;
}
