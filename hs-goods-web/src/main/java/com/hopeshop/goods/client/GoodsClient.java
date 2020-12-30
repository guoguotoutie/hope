package com.hopeshop.goods.client;

import com.hopeshop.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {
}