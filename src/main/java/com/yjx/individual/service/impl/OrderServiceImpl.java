package com.yjx.individual.service.impl;

import com.yjx.individual.entity.Orders;
import com.yjx.individual.mapper.OrderMapper;
import com.yjx.individual.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {

}
