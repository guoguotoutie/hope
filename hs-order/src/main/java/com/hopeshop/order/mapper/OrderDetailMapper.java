package com.hopeshop.order.mapper;

import com.hopeshop.order.pojo.OrderDetail;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @Author: silence
 * @Time: 2018-10-27 16:33
 * @Feature:
 */
public interface OrderDetailMapper extends Mapper<OrderDetail>, InsertListMapper<OrderDetail> {
}
