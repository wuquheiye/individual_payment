package com.yjx.individual.common.exception;


public class EnumNotFindException extends GlobalException{
    public EnumNotFindException(CodeMsg codeMsg) {
        super(codeMsg);
    }

    public EnumNotFindException(CodeMsg codeMsg, Throwable e) {
        super(codeMsg, e);
    }
}
