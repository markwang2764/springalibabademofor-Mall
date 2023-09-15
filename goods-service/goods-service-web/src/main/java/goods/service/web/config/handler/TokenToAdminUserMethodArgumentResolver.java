package goods.service.web.config.handler;

import com.example.springcloudalibabacommon.dto.Result;
import com.example.springcloudalibabacommon.exception.CloudException;
import com.example.springcloudalibabauserapi.openfeign.CloudAdminUserServiceFeign;
import goods.service.web.config.annotation.TokenToAdminUser;
import goods.service.web.entity.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.LinkedHashMap;

/**
 * @program: SpringCloudAlibabaDemo
 * @description: check Token
 * @author: Mr.markWang 2764
 * @create: 2023-09-07 17:22
 **/
@Component
public class TokenToAdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Lazy
    @Autowired
    private CloudAdminUserServiceFeign cloudAdminUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToAdminUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public UserAdmin resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToAdminUser.class) != null) {
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                Result<Object> result = cloudAdminUserService.getAdminUserByToken(token);
                if (result == null || result.getResultCode() != 200 || result.getData() == null) {
                    CloudException.fail("ADMIN_NOT_LOGIN_ERROR");
                }
                LinkedHashMap<String, Object> resultData = (LinkedHashMap<String, Object>) result.getData();
                UserAdmin userAdmin = new UserAdmin();
                userAdmin.setAdminUserId(Long.valueOf(resultData.get("adminUserId").toString()));
                userAdmin.setLoginUserName(String.valueOf(resultData.get("loginUserName")));
                userAdmin.setNickName(String.valueOf(resultData.get("nickName")));
                userAdmin.setLocked(Byte.valueOf(resultData.get("locked").toString()));
                return userAdmin;
            } else {
                CloudException.fail("ADMIN_NOT_LOGIN_ERROR");
            }
        }
        return null;
    }
}
