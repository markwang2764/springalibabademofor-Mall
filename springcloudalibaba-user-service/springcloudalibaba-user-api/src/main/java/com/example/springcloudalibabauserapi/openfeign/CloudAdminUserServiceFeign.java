package com.example.springcloudalibabauserapi.openfeign;

import com.example.springcloudalibabacommon.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "cloud-user-service", path = "/users")
public interface CloudAdminUserServiceFeign {
    @GetMapping(value = "/admin/{token}")
    Result<Object> getAdminUserByToken(@PathVariable(value="token") String token);
}
