package com.yjx.individual.service;

import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
public interface IProductService extends IService<Product> {

    ReturnMsg insertProduct(Product product);

    ReturnMsg selectProduct();

    ReturnMsg delProduct(Integer pid);

    ReturnMsg updProduct(Product product);

    ReturnMsg isNameRepetition(String pName );



}
