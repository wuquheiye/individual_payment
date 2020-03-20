package com.yjx.individual.controller;


import com.alibaba.fastjson.JSONObject;
import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.Product;
import com.yjx.individual.entity.QrCode;
import com.yjx.individual.service.IQrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.yjx.individual.common.utils.IsNullUtil.isEmpty;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
@Slf4j
@RestController
@RequestMapping("/individual/qr-code")
@CrossOrigin
public class QrCodeController {

    @Resource
    private IQrCodeService iQrCodeService;

    /**
     * 二维码录入
     * @param product
     * @return
     */
    @PostMapping("/insertQrCode")
    ReturnMsg insertQrCode(@RequestBody QrCode product){
        log.info("QrCode      "+product.toString());

        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iQrCodeService.insertQrCode(product);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("商品录入失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 获取二维码列表
     * @return
     */
    @GetMapping("/selectQrCode")
    ReturnMsg selectQrCode(){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg =  iQrCodeService.selectQrCode();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取二维码列表失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 删除二维码
     * @param pid
     * @return
     */
    @GetMapping("/delQrCode")
    ReturnMsg delProduct(@RequestParam("qcid") Integer pid){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iQrCodeService.delQrCode(pid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("删除二维码失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 修改二维码信息
     * @param product
     * @return
     */
    @PostMapping("/updQrCode")
    ReturnMsg updQrCode(@RequestBody QrCode product){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iQrCodeService.updQrCode(product);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("修改二维码信息失败");
            msg.setMsg(e.getMessage());
        }
        return msg;

    }

    /**
     * 通过id获取二维码
     * @param qcid
     * @return
     */
    @GetMapping("/selectQrCodeByid")
    ReturnMsg selectQrCodeByid(@RequestParam("qcid")Integer qcid){

        ReturnMsg msg = new ReturnMsg();
        QrCode byId = iQrCodeService.getById(qcid);
        if(!isEmpty(byId)){
            msg.setStatus("200");
            msg.setStatusMsg("通过id获取二维码成功");
            msg.setMsg(byId);
        }else {
            msg.setStatus("201");
            msg.setStatusMsg("通过id获取二维码失败");
            msg.setMsg(byId);
        }
        return msg;
    }



    /**
     * 获取一个随机可用的二维码
     * @return
     */
    @PostMapping("/selectQrCodeOne")
    ReturnMsg selectQrCodeOne(@RequestBody JSONObject object){
//        @RequestParam("pid")Integer pid ,@RequestParam("oUid") Integer ouid, @RequestParam("qcType")Integer qcType
        Integer oUid = Integer.parseInt(object.getString("oUid"));
        Integer pid = Integer.parseInt(object.getString("pid"));
        Integer qcType = Integer.parseInt(object.getString("qcType"));

        Double pmoney= Double.parseDouble(object.getString("pMoeny"));
        log.info("============"+oUid);
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iQrCodeService.selectQrCodeOne(oUid, pid, qcType,pmoney);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("204");
            msg.setStatusMsg("获取一个随机可用的二维码失败");
            msg.setMsg(e.getMessage());
        }
        return msg;

    }



}
