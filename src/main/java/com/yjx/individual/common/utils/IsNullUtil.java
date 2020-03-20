package com.yjx.individual.common.utils;

import com.yjx.individual.entity.AndroidListen;
import com.yjx.individual.entity.Product;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * yjx
 */
public class IsNullUtil {

    // ture 空   false 非空

    /**
     * 判断对象是否为空，且对象的所有属性都为空
     * ps: boolean类型会有默认值false 判断结果不会为null 会影响判断结果
     *     序列化的默认值也会影响判断结果
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object){
        if(object == null){
            return true;
        }
        Class clazz = (Class)object.getClass(); // 得到类对象
        Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
        boolean flag = true; //定义返回结果，默认为true
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(object); //得到属性值
                Type fieldType =field.getGenericType();//得到属性类型
                String fieldName = field.getName(); // 得到属性名
//                System.out.println("属性类型："+fieldType+",属性名："+fieldName+",属性值："+fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue != null){  //只要有一个属性值不为null 就返回false 表示对象不为null
                flag = false;
                break;
            }
        }
        return flag;

    }

    /**
     * 对象组中是否存在 Empty Object
     * @param os 对象组
     * @return
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if(isEmpty(o)){
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否全是 Empty Object
     * @param os
     * @return
     */
    public static boolean isAllEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }
    //判断集合是否为空
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    //判断Map是否为空
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    //判断数组是否为空
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    //判断List是否为空
    public static boolean isEmpty(List<Object> list) {
        return list == null || list.size() == 0;
    }

    //判断字符串是否为空
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }


    /**
     * 判断字符串是否全是数字
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        AndroidListen androidListen = new AndroidListen();
//////        androidListen.setAlId(1);
////        boolean allFieldNull = isEmpty(androidListen);
////        System.out.println(allFieldNull);
////        System.out.println(androidListen.toString());
////
//        String a="fdsaf";
//        System.out.println(StringUtils.isEmpty(a));
//        Product product =new Product();
        List list = new ArrayList();
        list.add("fdas");


        System.out.println(!isEmpty(list));

//        String a="0.01";
//        double v = Double.parseDouble(a);
//        System.out.println(v);

    }


}
