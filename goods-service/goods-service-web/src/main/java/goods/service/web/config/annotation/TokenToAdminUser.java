package goods.service.web.config.annotation;

import java.lang.annotation.*;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: token annotation
 * @author: Mr.markWang 2764
 * @create: 2023-09-07 17:23
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToAdminUser {
    String value() default "adminUser";
}
