package com.yjx.individual.mapper;

import com.yjx.individual.common.vo.QrcodeProductVo;
import com.yjx.individual.entity.QrCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁君选
 * @since 2020-03-10
 */
public interface QrCodeMapper extends BaseMapper<QrCode> {

//    @Select("SELECT qc.* ,p.p_name ,p.p_money FROM qr_code qc JOIN product p ON qc.qc_pid = p.pid")
    @Select("SELECT qc.*  FROM qr_code qc ")
    List<QrcodeProductVo> QrcodeProductVoList();

}
