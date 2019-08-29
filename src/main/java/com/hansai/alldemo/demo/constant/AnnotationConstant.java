package com.hansai.alldemo.demo.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 17:15
 */

@Retention(RetentionPolicy.SOURCE)
public @interface AnnotationConstant {
    int Spring = 1;
    int Summer = 2;
    int Antumn = 3;
    int Winter = 4;
}


