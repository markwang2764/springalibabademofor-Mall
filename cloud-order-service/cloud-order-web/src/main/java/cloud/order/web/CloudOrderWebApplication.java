package cloud.order.web;

import cloud.user.api.CloudAdminUserServiceFeign;
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
 * @create: 2023-10-21 14:44
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cloud.order.web.dao")
@EnableFeignClients(basePackageClasses = {CloudAdminUserServiceFeign.class, GoodsServiceApiOpenFeign.class})
public class CloudOrderWebApplication {
    public static void main(String[] args) {
//        System.setProperties("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(CloudOrderWebApplication.class, args);
    }
}
