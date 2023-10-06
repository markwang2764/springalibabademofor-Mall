package goods.service.api.openfeign;

import com.example.springcloudalibabacommon.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: feign client
 * @author: Mr.markWang 2764
 * @create: 2023-09-09 14:35
 **/
@FeignClient(value = "goods-service", path = "/goods")
public interface GoodsServiceApiOpenFeign {
    @GetMapping(value = "/admin/goodsDetail")
    Result<?> getGoodsDetail(@RequestParam(value = "goodsId") Long goodsId);

}
