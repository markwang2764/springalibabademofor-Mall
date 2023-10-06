package cloud.user.web.config;

import cloud.user.web.config.handler.TokenToAdminUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: mvc configurer
 * @author: Mr.markWang 2764
 * @create: 2023-09-08 01:34
 **/
@Configuration
public class AdminUserWebMvcConfigurer  extends WebMvcConfigurationSupport {
    @Autowired
    private TokenToAdminUserMethodArgumentResolver tokenToAdminUserMethodArgumentResolver;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolverList) {
        argumentResolverList.add(tokenToAdminUserMethodArgumentResolver);
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("===========");
        registry.addViewController("/swagger-ui")
                .setViewName("forward:/swagger-ui/index.html");
    }
}
