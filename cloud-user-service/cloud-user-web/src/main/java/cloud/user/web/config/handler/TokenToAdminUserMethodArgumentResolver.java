package cloud.user.web.config.handler;

import cloud.common.springcloud.exception.CloudException;
import cloud.common.springcloud.pojo.AdminUserToken;
import cloud.user.web.config.annotation.TokenToAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: check Token
 * @author: Mr.markWang 2764
 * @create: 2023-09-07 17:22
 **/
@Component
public class TokenToAdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToAdminUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public AdminUserToken resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToAdminUser.class) != null){
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32){
                ValueOperations<String, Object> opsForAdminUserToken = redisTemplate.opsForValue();
                AdminUserToken adminUserToken = (AdminUserToken) opsForAdminUserToken.get(token);
                if (adminUserToken == null){
                    CloudException.fail("ADMIN_NOT_LOGIN_ERROR");
                }
                return adminUserToken;
            } else {
                CloudException.fail("ADMIN_NOT_LOGIN_ERROR");
            }
        }

        return null;
    }
}
