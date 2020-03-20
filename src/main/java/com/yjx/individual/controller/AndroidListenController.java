package com.yjx.individual.controller;


import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.AndroidListen;
import com.yjx.individual.service.IAndroidListenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-03
 */
@Slf4j
@RestController
@RequestMapping("/individual/android-listen")
public class AndroidListenController  {

    @Autowired
    private IAndroidListenService iAndroidListenService;

    @PostMapping("/AndroidListen")
    public ReturnMsg AndroidListen(@RequestBody AndroidListen androidListen){
        System.out.println("进入后台");
        log.info(androidListen.toString());
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iAndroidListenService.insertAndroidListen(androidListen);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("订单录入失败");
            msg.setMsg(e.getMessage());
        }
        return msg;


//        return androidListen.toString();
//        boolean b = iAndroidListenService.insertAndroidListen(androidListen);
//        if(b){
//            return null;
//        }
//        return "";
    }

}
