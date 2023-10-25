package cloud.user.web.config.handler;

import cloud.common.springcloud.enums.ServiceResultEnum;
import cloud.common.springcloud.exception.CloudException;
import cloud.common.springcloud.pojo.MallUserToken;
import cloud.user.web.config.annotation.TokenToMallUser;
import cloud.user.web.entity.MallUser;
import cloud.user.web.mapper.MallUserMapper;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-22 12:13
 **/
public class TokenToMallUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private MallUserMapper mallUserMapper;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToMallUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToMallUser.class) instanceof TokenToMallUser){
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                ValueOperations<String, MallUserToken> opsForMallUserToken = redisTemplate.opsForValue();
                MallUserToken mallUserToken = opsForMallUserToken.get(token);
                if (mallUserToken == null) {
                    CloudException.fail(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult());
                }
                MallUser mallUser = mallUserMapper.selectById(mallUserToken.getUserId());
                if (mallUser == null) {
                    CloudException.fail(ServiceResultEnum.USER_NULL_ERROR.getResult());
                }
                if (mallUser.getLockedFlag().intValue() == 1) {
                    CloudException.fail(ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult());
                }
                return mallUserToken;
            }
            else {
                CloudException.fail(ServiceResultEnum.NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength; i++) {
            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1) {
                break;
            }
            i+=readlen;
        }
        return buffer;
    }
}
