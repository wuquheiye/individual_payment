package com.yjx.individual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjx.individual.common.exception.ServiceException;
import com.yjx.individual.common.utils.DataTimeUtil;
import com.yjx.individual.common.utils.DateUtil;
import com.yjx.individual.common.utils.LocalDateUtil;
import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.Product;
import com.yjx.individual.mapper.ProductMapper;
import com.yjx.individual.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static com.yjx.individual.common.utils.IsNullUtil.isEmpty;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
@Service
//@CacheConfig(cacheManager = "cacheManager", cacheNames = {"localDefaultCache"})
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    //	 定义一个静态的日志器变量
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);


    @Resource
    private ProductMapper productMapper;


    @Override
//    @CachePut(value = "product", keyGenerator = "keyGenerator")
    public ReturnMsg insertProduct(Product product) {

        ReturnMsg msg = new ReturnMsg();
        if(isEmpty(product)){
            throw new ServiceException("申请参数为空");
        }
        logger.info(" =================="+product.toString());
        product.setPTime(LocalDateUtil.getLocalDateTime());
        int save = productMapper.insert(product);
        if(save>0){
            msg.setStatus("200");
            msg.setMsg(save);
            msg.setStatusMsg("商品录入成功");
        }
        return msg;
    }

    @Override
//    @Cacheable(value = "product", keyGenerator = "keyGenerator")
    public ReturnMsg selectProduct() {
        ReturnMsg msg = new ReturnMsg();
        QueryWrapper qw = new QueryWrapper();
        List<Product> list = productMapper.selectList(qw);
        if(!isEmpty(list)){
            msg.setStatus("200");
            msg.setMsg(list);
            msg.setStatusMsg("获取商品列表成功");
        }else {
            msg.setStatus("201");
            msg.setMsg(list);
            msg.setStatusMsg("商品列表为空");
        }
        return msg;
    }

    @Override
//    @CachePut(value = "product", keyGenerator = "keyGenerator")
    public ReturnMsg delProduct(Integer pid) {
        ReturnMsg msg = new ReturnMsg();
        int i = productMapper.deleteById(pid);
        if(i>0){
            msg.setStatus("200");
            msg.setMsg(i);
            msg.setStatusMsg("商品删除成功");
        }
        return msg;
    }

    @Override
    @CachePut(value = "product",key = "#product.pName")
    public ReturnMsg updProduct(Product product) {
        if(isEmpty(product)){
            throw new ServiceException("申请参数为空");
        }
        ReturnMsg msg = new ReturnMsg();
        int i = productMapper.updateById(product);
        if(i>0){
            msg.setStatus("200");
            msg.setMsg(i);
            msg.setStatusMsg("商品修改成功");
        }
        return msg;
    }

    @Cacheable(value ="product", key = "#pName")
    @Override
    public ReturnMsg isNameRepetition(String pName) {
        if(isEmpty(pName)){
            throw new ServiceException("申请参数为空");
        }
        logger.info("pName    "+pName);
        ReturnMsg msg = new ReturnMsg();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("p_name",pName);
        List list = productMapper.selectList(qw);
        logger.info("SQL查询后"+list);
        if(!isEmpty(list)){
            msg.setStatus("200");
            msg.setMsg(list);
            msg.setStatusMsg("商品名字已存在");
        }
        return msg;
    }
}
