package com.yjx.individual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjx.individual.common.enums.OStatsusEnum;
import com.yjx.individual.common.enums.QcStatusEnum;
import com.yjx.individual.common.exception.ServiceException;
import com.yjx.individual.common.utils.LocalDateUtil;
import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.common.websocket.WebSocketServer;
import com.yjx.individual.entity.AndroidListen;
import com.yjx.individual.entity.Orders;
import com.yjx.individual.entity.QrCode;
import com.yjx.individual.mapper.AndroidListenMapper;
import com.yjx.individual.mapper.OrderMapper;
import com.yjx.individual.mapper.QrCodeMapper;
import com.yjx.individual.service.IAndroidListenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.io.IOException;

import static com.yjx.individual.common.utils.IsNullUtil.isEmpty;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-03
 */
@Slf4j
@Service
@Transactional
public class AndroidListenServiceImpl extends ServiceImpl<AndroidListenMapper, AndroidListen> implements IAndroidListenService {



    @Resource
    private AndroidListenMapper androidListenMapper;

    @Resource
    private QrCodeMapper qrCodeMapper;

    @Resource
    private OrderMapper orderMapper;

    public ReturnMsg insertAndroidListen(AndroidListen androidListen) throws IOException {
        if(isEmpty(androidListen)) throw new ServiceException("参数不能为空");
        ReturnMsg msg = new ReturnMsg();
        androidListen.setAlOrderTime(LocalDateUtil.getLocalDateTime());

        //判断是微信还是支付宝
        Integer qc_type=1;

        log.info("进入判断"+androidListen.getAlType());
        if(androidListen.getAlType() == "wechat"){
            qc_type =1;
            log.info("支付类型"+qc_type +" wechat ");
        }else if(androidListen.getAlType()=="alipay"){
            qc_type = 0;
            log.info("支付类型"+qc_type +" alipay ");
        }else if(androidListen.getAlType() == "alipay-transfer"){
            qc_type =0;
            log.info("支付类型"+qc_type +" alipay-transfer");
        }


        // 录入监听到信息
        int insert = androidListenMapper.insert(androidListen);

        log.info("查询二维码"+qc_type +" 查询");
        if(insert!=0){
            //通过 money 比对 正在使用的 订单
            //先获取指定的二维码
            QrCode qrCode = qrCodeMapper.selectOne(new QueryWrapper<QrCode>()
                    .eq("qc_money", androidListen.getAlMoney())
                    .eq("qc_status", QcStatusEnum.YES.getCode())
                    .eq("qc_type",qc_type)
//                    .gt("qc_call_time",LocalDateUtil.getLocalDateTime(0, 5))
                    );
            if(!isEmpty(qrCode)){
                //通过二维码id 查询 订单
                Orders orders = orderMapper.selectOne(new QueryWrapper<Orders>()
                        .eq("o_qcid", qrCode.getQcId())
                        .eq("o_status",OStatsusEnum.NO.getCode())
                        .gt("o_place_time", LocalDateUtil.getLocalDateTime(0, 5)));
                if(!isEmpty(orders)){
                    //将 将监听内容 录入到相应订单列表中 并更改订单状态
                    Orders orders1 = new Orders();
                    orders1.setOid(orders.getOid());
                    orders1.setOAlId(androidListen.getAlId());
                    orders1.setOStatus(OStatsusEnum.YES.getCode());
                    int i = orderMapper.updateById(orders1);
//                    //释放二维码使用时间
                    QrCode qrCode1 =new QrCode();
                    qrCode1.setQcId(qrCode.getQcId());
                    qrCode1.setQcStatus(QcStatusEnum.NO.getCode());
                    log.info("释放二维码使用时间"+qrCode1.getQcCallTime()
                            +"当前二维码id"+qrCode1.getQcId()
                            +"释放时间"+LocalDateUtil.getLocalDateTime(0, 10));
                    int i1 = qrCodeMapper.updateById(qrCode1);


                    if(i>0 && i1>0){
                        String sid = Integer.toString(orders.getOUid());

                        WebSocketServer.sendInfo(
                                "支付成功，支付金额"+androidListen.getAlMoney()
                                ,sid);

                        msg.setStatus("200");
                        msg.setStatusMsg("订单成功");
                        msg.setMsg(androidListen);
                    }else {throw  new ServiceException("监听内容录入 订单失败 订单未完成");}
                }else { throw  new ServiceException("此二维码已过期 或 此二维码 无相应订单");}
            }else {
                throw  new ServiceException("money 无匹配二维码");
            }

        }else {
            throw new ServiceException("监听信息录入失败");
        }
        return msg;

    }


}
