package recommend.service.web;

import com.example.springcloudalibabauserapi.openfeign.CloudAdminUserServiceFeign;
import goods.service.api.openfeign.GoodsServiceApiOpenFeign;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-09-17 21:32
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("recommend.service.web.dao")
@EnableFeignClients(basePackageClasses = {
        CloudAdminUserServiceFeign.class,
        GoodsServiceApiOpenFeign.class
})
public class RecommendServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecommendServiceApplication.class, args);
    }
}
