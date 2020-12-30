package com.hopeshop.item.api;

import com.hopeshop.item.pojo.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SpecificationApi {
    @GetMapping("spec/bycategoryid/{id}")
    public Specification querySpecByCategoryId(@PathVariable("id") Long id);
}
