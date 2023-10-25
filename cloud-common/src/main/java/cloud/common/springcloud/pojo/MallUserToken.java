package cloud.common.springcloud.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-22 12:18
 **/
@Data
public class MallUserToken implements Serializable {
    private Long userId;
    private String token;
}
