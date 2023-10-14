package cloud.user.web.config;

import cloud.common.springcloud.pojo.AdminUserToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: swagger congig
 * @author: Mr.markWang 2764
 * @create: 2023-09-08 01:24
 **/
@Configuration
@EnableOpenApi
public class AdminUserSwagger3Config {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(AdminUserToken.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cloud.user.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("登录认证token")
                .required(false)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build()
        );
        return parameters;
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("user service 接口文档")
                .description("swagger接口文档")
                .version("2.0")
                .build();
    }
}
