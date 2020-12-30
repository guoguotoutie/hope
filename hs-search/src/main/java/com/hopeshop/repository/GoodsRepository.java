package com.hopeshop.repository;

import com.hopeshop.pojo.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
