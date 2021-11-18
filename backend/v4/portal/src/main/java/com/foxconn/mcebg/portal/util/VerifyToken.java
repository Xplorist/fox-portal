package com.foxconn.mcebg.portal.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * VerifyToken註解類：
 * 加到controller方法上表示该方法需要验证token
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyToken {
    boolean required() default true;
}
