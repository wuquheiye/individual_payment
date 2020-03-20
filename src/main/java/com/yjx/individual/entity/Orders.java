package com.yjx.individual.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
@Data
@Accessors(chain = true)
public class Orders implements Serializable {



    /**
     * 订单id
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 订单号
     */
    private String oOrderCode;

    /**
     * 商品id
     */
    private Integer oPid;

    /**
     * 用户id
     */
    private Integer oUid;

    /**
     * 使用二维码id
     */
    private  Integer oQcid;

    /**
     * 关联监听id
     */
    private Integer oAlId;

    /**
     * 下单时间
     */
    private LocalDateTime oPlaceTime;

    /**
     * 订单状态
     */
    private Integer oStatus;



}
