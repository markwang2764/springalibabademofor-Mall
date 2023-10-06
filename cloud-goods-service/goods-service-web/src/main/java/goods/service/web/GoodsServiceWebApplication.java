package goods.service.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("goods.service.web.dao")
@EnableFeignClients(basePackageClasses = {com.example.springcloudalibabauserapi.openfeign.CloudAdminUserServiceFeign.class})
@EnableWebMvc
public class GoodsServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceWebApplication.class, args);
    }

}
