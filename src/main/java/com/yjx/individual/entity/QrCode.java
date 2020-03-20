package com.yjx.individual.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
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
public class QrCode implements Serializable {



    /**
     * 二维码标识id
     */
    @TableId(value = "qc_id", type = IdType.AUTO)
    private Integer qcId;

    /**
     * 二维码base64
     */
    private String qcBase64;

    /**
     * 0未使用 1 待支付
     */
    private Integer qcStatus;

    /**
     * 最后调用时间
     */
    private LocalDateTime qcCallTime;

    /**
     * 二维码标识商品id
     */
//    private Integer qcPid;

    /**
     * 二维码类型 0支付宝 2微信
     */
    private Integer qcType;

    /**
     *二维码金额
     */
    private Double qcMoney;


}
