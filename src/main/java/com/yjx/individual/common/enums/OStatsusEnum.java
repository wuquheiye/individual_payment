package com.yjx.individual.common.enums;

import com.yjx.individual.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  OStatsusEnum implements BaseEnum<Integer>{
    NO(0,"未支付"),
    YES(1,"已支付");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for(OStatsusEnum qcStatusEnum :OStatsusEnum.values()) {
            if(qcStatusEnum.code.equals(code)) {
                return qcStatusEnum.getDesc();
            }
        }
        throw  new ServiceException("OStatsusEnum  No matching constant for [" + code + "]");
    }
    public static OStatsusEnum getByCode(Integer value){
        for(OStatsusEnum  role : values()){
            if (role.getCode() == value) {
                return role;
            }
        }
        return null;
    }
}
