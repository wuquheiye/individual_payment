package com.yjx.individual.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Product  implements Serializable {
    /**
     * 商品id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 商品name
     */
    private String pName;

    /**
     * 商品金额
     */
    private Double pMoney;

    /**
     * 商品创建时间
     */
    private LocalDateTime pTime;


}
