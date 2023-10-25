package cloud.gateway.mall.filter;

import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.dto.ResultGenerator;
import cloud.common.springcloud.pojo.MallUserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-22 23:55
 **/
public class ValidMallUserTokenGlobalFilter implements GlobalFilter, Ordered {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final List<String> ignoreURLS = new ArrayList<>();
        ignoreURLS.add("/users/guests/register");
        ignoreURLS.add("/users/guests/login");
        ignoreURLS.add("/users/guests/listAll");
        if (ignoreURLS.contains(exchange.getRequest().getURI().getPath())) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if (headers.isEmpty()) {
            return wrapErrorResponse(exchange, chain);
        }
        String token = headers.getFirst("token");
        if (!StringUtils.hasText(token)) {
            return wrapErrorResponse(exchange, chain);
        }
        ValueOperations<String, MallUserToken> opsForMallUserToken = redisTemplate.opsForValue();
        MallUserToken tokenObject = opsForMallUserToken.get(token);
        if (tokenObject == null) {
            return wrapErrorResponse(exchange, chain);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    Mono<Void> wrapErrorResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        Result<?> result = ResultGenerator.genErrorResult(416, "无权限访问");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode resultNode = mapper.valueToTree(result);
        byte[] bytes = resultNode.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(bytes);
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return exchange.getResponse().writeWith(Flux.just(dataBuffer));
    }
}
