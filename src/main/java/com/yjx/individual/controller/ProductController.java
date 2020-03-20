package com.yjx.individual.controller;


import com.yjx.individual.common.exception.ServiceException;
import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.Product;
import com.yjx.individual.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.yjx.individual.common.utils.IsNullUtil.isEmpty;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
@Slf4j
@RestController
@RequestMapping("/individual/product")
@CrossOrigin
public class ProductController  {

    @Resource
    private IProductService iProductService;

    /**
     * 商品录入
     * @param product
     * @return
     */
    @PostMapping("/insertProduct")
    ReturnMsg insertProduct(@RequestBody Product product){
        log.info(" =====静入商品录入============="+product.toString());

        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iProductService.insertProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("商品录入失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/selectProduct")
    ReturnMsg selectProduct(){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg =  iProductService.selectProduct();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取商品列表失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 删除商品
     * @param pid
     * @return
     */
    @GetMapping("/delProduct")
    ReturnMsg delProduct(@RequestParam("pid") Integer pid){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iProductService.delProduct(pid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("删除商品信息失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @PostMapping("/updProduct")
    ReturnMsg updProduct(@RequestBody Product product){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iProductService.updProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("修改商品信息失败");
            msg.setMsg(e.getMessage());
        }
        return msg;

    }


    /**
     * 判断商品名字是否重复
     * @param pName
     * @return
     */
    @GetMapping("/isNameRepetition")
    ReturnMsg isNameRepetition(@RequestParam("pname")String pName ){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iProductService.isNameRepetition(pName);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("判断商品名字是否重复失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }


}
