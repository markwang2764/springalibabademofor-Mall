package cloud.user.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-22 11:30
 **/
@Configuration
@MapperScan("cloud.user.web.mapper")
public class MybatisPlusConfig {
}
