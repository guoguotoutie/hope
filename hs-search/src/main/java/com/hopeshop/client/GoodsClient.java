package com.hopeshop.client;

import com.hopeshop.common.pojo.PageResult;
import com.hopeshop.item.api.GoodsApi;
import com.hopeshop.item.pojo.SpuBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {




}
