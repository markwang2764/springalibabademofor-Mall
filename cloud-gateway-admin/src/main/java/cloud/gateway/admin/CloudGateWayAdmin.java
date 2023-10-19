package cloud.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGateWayAdmin {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateWayAdmin.class, args);
    }

}
