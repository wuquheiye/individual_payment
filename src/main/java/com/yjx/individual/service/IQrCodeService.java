package com.yjx.individual.service;

import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.QrCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
public interface IQrCodeService extends IService<QrCode> {


    ReturnMsg insertQrCode(QrCode qrCode);

    ReturnMsg selectQrCode();

    ReturnMsg delQrCode(Integer qcid);

    ReturnMsg updQrCode(QrCode qrCode);

    ReturnMsg selectQrCodeOne(Integer uid,Integer pid ,Integer qcType,Double pmoney);

//    ReturnMsg isNameRepetitionQrCode(String pName );

}
