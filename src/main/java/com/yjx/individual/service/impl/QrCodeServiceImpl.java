package com.yjx.individual.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjx.individual.common.enums.OStatsusEnum;
import com.yjx.individual.common.enums.QcStatusEnum;
import com.yjx.individual.common.exception.ServiceException;
import com.yjx.individual.common.utils.LocalDateUtil;
import com.yjx.individual.common.utils.UUIDutil;
import com.yjx.individual.common.vo.QrcodeProductVo;
import com.yjx.individual.common.vo.ReturnMsg;
import com.yjx.individual.entity.Orders;
import com.yjx.individual.entity.QrCode;
import com.yjx.individual.mapper.OrderMapper;
import com.yjx.individual.mapper.QrCodeMapper;
import com.yjx.individual.service.IQrCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.yjx.individual.common.utils.IsNullUtil.isEmpty;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
@Slf4j
@Service
@Transactional
public class QrCodeServiceImpl extends ServiceImpl<QrCodeMapper, QrCode> implements IQrCodeService {

    @Resource
    private QrCodeMapper qrCodeMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public ReturnMsg insertQrCode(QrCode qrCode) {
        ReturnMsg msg = new ReturnMsg();
        if(isEmpty(qrCode)){
            throw new ServiceException("申请参数为空");
        }
        log.info(" =================="+qrCode.toString());
        qrCode.setQcCallTime(LocalDateUtil.getLocalDateTime(0,5));
        int save = qrCodeMapper.insert(qrCode);
        if(save>0){
            msg.setStatus("200");
            msg.setMsg(save);
            msg.setStatusMsg("二维码录入成分");
        }
        return msg;
    }

    @Override
    public ReturnMsg selectQrCode() {
        ReturnMsg msg = new ReturnMsg();
        List<QrcodeProductVo> list = qrCodeMapper.QrcodeProductVoList();
        if(!isEmpty(list)){
            for(QrcodeProductVo qrcodeProductVo: list){
                if(LocalDateUtil.localDateTimeCompare(
                        qrcodeProductVo.getQcCallTime(),LocalDateUtil.getLocalDateTime
                                (0,5))){
                    qrcodeProductVo.setQcStatus(QcStatusEnum.YES.getCode());
                    log.info("当前判断二维码状态"+qrcodeProductVo.getQcStatus());
                }
                log.info("当前二维码状态"+qrcodeProductVo.getQcStatus());
            }
            msg.setStatus("200");
            msg.setMsg(list);
            msg.setStatusMsg("获取二维码列表成功");
        }else {
            msg.setStatus("201");
            msg.setMsg(list);
            msg.setStatusMsg("二维码列表为空");
        }
        return msg;
    }

    @Override
    public ReturnMsg delQrCode(Integer qcid) {
        ReturnMsg msg = new ReturnMsg();
        int i = qrCodeMapper.deleteById(qcid);
        if(i>0){
            msg.setStatus("200");
            msg.setMsg(i);
            msg.setStatusMsg("二维码删除成功");
        }
        return msg;
    }

    @Override
    public ReturnMsg updQrCode(QrCode qrCode) {
        if(isEmpty(qrCode)){
            throw new ServiceException("申请参数为空");
        }
        ReturnMsg msg = new ReturnMsg();
        int i = qrCodeMapper.updateById(qrCode);
        if(i>0){
            msg.setStatus("200");
            msg.setMsg(i);
            msg.setStatusMsg("二维码修改成功");
        }
        return msg;
    }

    @Override
    public ReturnMsg selectQrCodeOne(Integer uid, Integer pid, Integer qcType ,Double pmoney) {
        if(uid == null || uid == 0)throw new ServiceException("uid申请参数为空");
        if( pid == 0)throw new ServiceException("pMoney申请参数为空");
        if(qcType == null )throw new ServiceException("qcType申请参数为空");
        ReturnMsg msg = new ReturnMsg();
        QueryWrapper qworder = new QueryWrapper();
        qworder.eq("o_uid", uid);
        qworder.eq("o_status", OStatsusEnum.NO.getCode());
        qworder.gt("o_place_time",LocalDateUtil.getLocalDateTime(0,5));
        //判断此用户 是否 有未完成的订单
        Orders orders1 = orderMapper.selectOne(qworder);
        log.info("fdsafaas"+orders1);
        if(!isEmpty(orders1)){

            msg.setStatus("200");
            msg.setMsg(orders1);
            msg.setStatusMsg("二维码返回成功，你有一个订单未支付");
            return msg;
        }

        double v = pmoney - 0.10;
        double v1 = pmoney + 0.10;
        if(v<0){
            v = 0.01;
        }
            //返回二维码
            List<QrCode> qc_type = qrCodeMapper.selectList(
                    new QueryWrapper<QrCode>()
                            .eq("qc_type", qcType)
                            .ge("qc_money",v)
                            .le("qc_money",v1)
//                            .eq("qc_status", QcStatusEnum.NO.getCode())
            );
            if(!isEmpty(qc_type)){
                log.info("进入判断 二维码 五分钟之内是否 使用过 或者 二维码使用状态为0的");
                for(QrCode qrcode : qc_type){
                    if(!LocalDateUtil.localDateTimeCompare(
                            qrcode.getQcCallTime(),LocalDateUtil.getLocalDateTime
                                    (0,5)) || qrcode.getQcStatus()==QcStatusEnum.NO.getCode())
                    {
                        log.info("判断时间5分钟之内变化");
                        //调用二维码更新它的调用时间  标记其已被使用
                        QrCode qrCodeUpd = new QrCode();
                        qrCodeUpd.setQcCallTime(LocalDateUtil.getLocalDateTime());
                        qrCodeUpd.setQcId(qrcode.getQcId());
                        qrCodeUpd.setQcStatus(QcStatusEnum.YES.getCode());
                        int i = qrCodeMapper.updateById(qrCodeUpd);
                        if(i>0){
                            //生成订单
                            Orders orders = new Orders();
                            orders.setOUid(uid);
                            orders.setOPid(pid);
                            orders.setOOrderCode(UUIDutil.uuid());
                            orders.setOPlaceTime(LocalDateUtil.getLocalDateTime());
                            orders.setOQcid(qrcode.getQcId());
                            int insert = orderMapper.insert(orders);
                            if(insert >0 ){
                                msg.setStatus("201");
                                msg.setMsg(qrcode);
                                msg.setStatusMsg("二维码返回成功");
                                return  msg;
                            }else {
                                throw new ServiceException("订单生成失败");
                            }

                        }else {
                            throw new ServiceException("修改二维码状态是失败");
                        }

                    }

                }
                throw new ServiceException("当前无空闲的二维码");
            }else {
                msg.setStatus("203");
                msg.setMsg("此商品暂不出售");
                msg.setStatusMsg("此商品暂不出售");
            }


        return msg;
    }
}
