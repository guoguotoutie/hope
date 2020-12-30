package com.hopeshop.client;

import com.hopeshop.item.api.CategoryApi;
import com.hopeshop.item.pojo.Category;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
