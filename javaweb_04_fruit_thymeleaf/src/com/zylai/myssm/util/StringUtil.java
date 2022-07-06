package com.zylai.myssm.util;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/26/11:18
 * @Description:
 */
public class StringUtil {
//    判断字符串是否null或者""
    public static boolean isEmpty(String str){
        return str==null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
