package goods.service.api.openfeign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: feign client
 * @author: Mr.markWang 2764
 * @create: 2023-09-09 14:35
 **/
@FeignClient(value = "goods-service", path = "/goods")
public interface GoodsServiceApiOpenFeign {

}
