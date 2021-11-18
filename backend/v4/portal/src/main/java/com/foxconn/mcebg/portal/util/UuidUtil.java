package com.foxconn.mcebg.portal.util;

import java.util.UUID;

/**
 * UUID工具類
 */
public class UuidUtil {
    /**
     * 獲取UUID
     *
     * @return 返回32位全大寫的UUID
     */
    public static String getUUID32() {
        String result = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        return result;
    }
}
