package com.yjx.individual.service;

import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.AndroidListen;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjx.individual.mapper.AndroidListenMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-03
 */
public interface IAndroidListenService extends IService<AndroidListen> {

    ReturnMsg insertAndroidListen(AndroidListen androidListen) throws IOException;



}
