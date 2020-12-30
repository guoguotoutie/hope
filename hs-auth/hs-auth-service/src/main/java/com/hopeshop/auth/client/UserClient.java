package com.hopeshop.auth.client;

import com.hopeshop.pojo.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service")
public interface UserClient extends UserApi {
}
