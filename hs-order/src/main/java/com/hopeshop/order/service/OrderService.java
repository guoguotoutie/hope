package com.hopeshop.order.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hopeshop.auth.entity.UserInfo;
import com.hopeshop.cart.interceptor.LoginInterceptor;
import com.hopeshop.common.pojo.PageResult;
import com.hopeshop.common.util.IdWorker;
import com.hopeshop.order.mapper.OrderDetailMapper;
import com.hopeshop.order.mapper.OrderMapper;
import com.hopeshop.order.mapper.OrderStatusMapper;
import com.hopeshop.order.pojo.Order;
import com.hopeshop.order.pojo.OrderDetail;
import com.hopeshop.order.pojo.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by silence on 2020/1/26.
 */
@Service
public class OrderService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Order order) {
        //1.生成orderId
        long orderId = idWorker.nextId();
        //2.获取登录的用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        //3.初始化数据
        order.setBuyerNick(userInfo.getUsername());
        order.setBuyerRate(false);
        order.setCreateTime(new Date());
        order.setOrderId(orderId);
        order.setUserId(userInfo.getId());
        //4.保存数据
        this.orderMapper.insertSelective(order);

        //5.保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCreateTime(order.getCreateTime());
        //初始状态未未付款：1
        orderStatus.setStatus(1);
        //6.保存数据
        this.orderStatusMapper.insertSelective(orderStatus);

        //7.在订单详情中添加orderId
        order.getOrderDetails().forEach(orderDetail -> orderDetail.setOrderId(orderId));
        //8.保存订单详情，使用批量插入功能
        this.orderDetailMapper.insertList(order.getOrderDetails());
        //9.减库存

        logger.debug("生成订单，订单编号：{}，用户id：{}", orderId, userInfo.getId());
        return orderId;
    }

    /**
     * 根据订单号查询订单
     * @param id
     * @return
     */
    public Order queryOrderById(Long id) {
        //1.查询订单
        Example example1 = new Example(Order.class);
        example1.createCriteria().andEqualTo("orderId",id);
        Order order = (Order) this.orderMapper.selectOneByExample(example1);
        //2.查询订单详情
        Example example = new Example(OrderDetail.class);
        example.createCriteria().andEqualTo("orderId",id);
        List<OrderDetail> orderDetail = this.orderDetailMapper.selectByExample(example);
        //3.查询订单状态
        OrderStatus orderStatus = this.orderStatusMapper.selectByPrimaryKey(order.getOrderId());
        //4.order对象填充订单详情
        order.setOrderDetails(orderDetail);
        //5.order对象设置订单状态
        order.setStatus(orderStatus.getStatus());
        //6.返回order
        return order;
    }

    /**
     * 查询当前登录用户的订单，通过订单状态进行筛选
     * @param page
     * @param rows
     * @param status
     * @return
     */
    
    public PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status) {
        try{
            //1.分页
            PageHelper.startPage(page,rows);
            //2.获取登录用户
            UserInfo userInfo = LoginInterceptor.getLoginUser();
            //3.查询
            Page<Order> pageInfo = (Page<Order>) this.orderMapper.queryOrderList(userInfo.getId(), status);
            //4.填充orderDetail
            List<Order> orderList = pageInfo.getResult();
            orderList.forEach(order -> {
                Example example = new Example(OrderDetail.class);
                example.createCriteria().andEqualTo("orderId",order.getOrderId());
                List<OrderDetail> orderDetailList = this.orderDetailMapper.selectByExample(example);
                order.setOrderDetails(orderDetailList);
            });
            return new PageResult<>(pageInfo.getTotal(),(long)pageInfo.getPages(), orderList);
        }catch (Exception e){
            logger.error("查询订单出错",e);
            return null;
        }
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */

    public Boolean updateOrderStatus(Long id, Integer status) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(id);
        orderStatus.setStatus(status);
        //1.根据状态判断要修改的时间
        switch (status){
            case 2:
                //2.付款时间
                orderStatus.setPaymentTime(new Date());
                break;
            case 3:
                //3.发货时间
                orderStatus.setConsignTime(new Date());
                break;
            case 4:
                //4.确认收货，订单结束
                orderStatus.setEndTime(new Date());
                break;
            case 5:
                //5.交易失败，订单关闭
                orderStatus.setCloseTime(new Date());
                break;
            case 6:
                //6.评价时间
                orderStatus.setCommentTime(new Date());
                break;

            default:
                return null;
        }
        int count = this.orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
        return count == 1;
    }
}
