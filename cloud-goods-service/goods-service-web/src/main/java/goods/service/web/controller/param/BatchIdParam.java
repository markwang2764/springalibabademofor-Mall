package goods.service.web.controller.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: ids
 * @author: Mr.markWang 2764
 * @create: 2023-09-12 22:22
 **/
@Data
public class BatchIdParam implements Serializable {
    Long[] ids;
}
