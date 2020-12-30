package com.hopeshop.client;

import com.hopeshop.item.api.SpecificationApi;
import com.hopeshop.item.pojo.Specification;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi {

}
