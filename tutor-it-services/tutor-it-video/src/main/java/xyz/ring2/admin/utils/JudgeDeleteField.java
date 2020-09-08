package xyz.ring2.admin.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author :     ring2
 * @date :       2020/5/11 15:11
 * description:
 **/
public class JudgeDeleteField {

    public static boolean judgeDeleteField(Object object) {
        Class<?> aClass = object.getClass();
        try {
            aClass.getDeclaredField("deleted");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }


}
