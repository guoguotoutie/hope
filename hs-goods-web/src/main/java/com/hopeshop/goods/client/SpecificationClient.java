package com.hopeshop.goods.client;

import com.hopeshop.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by silence on 2020/1/8.
 */
@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi {
}
