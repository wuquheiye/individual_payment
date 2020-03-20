package com.yjx.individual.common.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class QrcodeProductVo  implements Serializable {


    /**
     * 二维码标识id
     */
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

//    /**
//     * 二维码标识商品id
//     */
//    private Integer qcPid;

    /**
     * 二维码类型 0支付宝 2微信
     */
    private Integer qcType;

    /**
     *二维码金额
     */
    private Double qcMoney;

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
