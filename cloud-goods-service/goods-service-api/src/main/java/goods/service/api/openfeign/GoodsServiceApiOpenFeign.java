package goods.service.api.openfeign;

import cloud.common.springcloud.dto.Result;
import goods.service.api.dto.GoodsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: feign client
 * @author: Mr.markWang 2764
 * @create: 2023-09-09 14:35
 **/
@FeignClient(value = "cloud-goods-service", path = "/goods")
public interface GoodsServiceApiOpenFeign {
    @GetMapping(value = "/admin/goodsDetail")
    Result<GoodsDTO> getGoodsDetail(@RequestParam(value = "goodsId") Long goodsId);

}
