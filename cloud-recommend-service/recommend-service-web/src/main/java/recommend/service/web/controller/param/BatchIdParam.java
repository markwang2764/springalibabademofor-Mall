package recommend.service.web.controller.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: batch id
 * @author: Mr.markWang 2764
 * @create: 2023-09-19 23:41
 **/
@Data
public class BatchIdParam implements Serializable {
    Long[] ids;
}
