package com.yjx.individual.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yjx.individual.common.utils.LocalDateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-03
 */
@Data
@Accessors(chain = true)
public class AndroidListen implements Serializable {

//    private static final long serialVersionUID = 1L;

    /**
     * 监听信息
     */
    @TableId(value = "al_id", type = IdType.AUTO)
    private Integer alId;

    /**
     * 支付类型
     */
    private String alType;

    /**
     * 监听到的金额
     */
    private Double alMoney;

    /**
     * 监听信息标题
     */
    private String alTitle;

    /**
     * 监听信息内容
     */
    private String alContent;

    /**
     * 监听信息时间
     */
    private String alTime;

    /**
     * 接收到监听信息时间
     */
    private LocalDateTime alOrderTime;


}
