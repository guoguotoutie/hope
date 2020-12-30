package com.hopeshop.order.mapper;

import com.hopeshop.order.pojo.Order;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: silence
 * @Time: 2018-10-27 16:31
 * @Feature:
 */
public interface OrderMapper extends Mapper<Order> {
    /**
     * 分页查询订单
     * @param userId
     * @param status
     * @return
     */
    List<Order> queryOrderList(@Param("userId") Long userId, @Param("status") Integer status);
}
