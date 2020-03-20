package com.yjx.individual.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author 袁君选
 * @Date 2020/2/20 11:32
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class AndroidNotificationListenerJson implements Serializable {

    private double money;
    private String time;
    private String title;
    /**
     * 支付类型
     */
    private String type;
    private String content;

}
