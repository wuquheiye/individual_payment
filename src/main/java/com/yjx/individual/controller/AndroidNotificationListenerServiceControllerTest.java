package com.yjx.individual.controller;

import com.yjx.individual.entity.AndroidNotificationListenerJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 袁君选
 * @Date 2020/2/20 11:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/anlsc")
public class AndroidNotificationListenerServiceControllerTest {

    @PostMapping("/notificationListener")
    public String notificationListener(@RequestBody AndroidNotificationListenerJson androidNotificationListenerJson){
        System.out.println("进入后台");
        System.out.println(androidNotificationListenerJson);
        return androidNotificationListenerJson.toString();
    }

}
