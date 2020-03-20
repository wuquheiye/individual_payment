package com.yjx.individual.common.utils;

import java.util.UUID;

/**
 * yjx
 * 封装uuid
 */
public class UUIDutil {
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     * @return
     */
    public static String uuid(){

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(uuid());
    }
}
