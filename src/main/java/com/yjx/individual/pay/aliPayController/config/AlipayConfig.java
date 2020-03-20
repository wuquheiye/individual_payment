package com.yjx.individual.pay.aliPayController.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:
 * @modifiedBy:
 * @date:
 * @description:
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfig{

    @Autowired
    private AlipayProperties properties;


    /**
     * alipay-sdk-java
     * @return
     */
    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(properties.getGatewayUrl(),
                properties.getAppid(),
                properties.getPrivateKey(),
                properties.getFormate(),
                properties.getCharset(),
                properties.getAlipayPublicKey(),
                properties.getSignType());
    }
}
