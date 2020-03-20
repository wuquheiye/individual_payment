package com.yjx.individual.common.enums;

import com.yjx.individual.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QcStatusEnum implements BaseEnum<Integer>{
    YES(1,"使用中"),
    NO(0,"未使用");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for(QcStatusEnum qcStatusEnum : QcStatusEnum.values()) {
            if(qcStatusEnum.code.equals(code)) {
                return qcStatusEnum.getDesc();
            }
        }
        throw  new ServiceException("QcStatus  No matching constant for [" + code + "]");
    }
    public static QcStatusEnum getByCode(Integer value){
        for( QcStatusEnum role : values()){
            if (role.getCode() == value) {
                return role;
            }
        }
        return null;
    }
}
