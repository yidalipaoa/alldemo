package com.hansai.alldemo.demo.constant;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import static com.hansai.alldemo.demo.constant.EnumConstant.Antumn;

/**
 * <p>Description: 注解式的常量</p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 17:17
 */
public class ConstantTest {

    @Test
    public void testAnnotation() {
        int a = AnnotationConstant.Antumn;
        ConstantTest.testAnnotation(100);



    }

    /**
     * 测试注解常量
     * @param season
     */
    public static void testAnnotation(@AnnotationConstant int season) {

        switch (season) {
            case AnnotationConstant.Antumn:
                System.out.println(AnnotationConstant.Antumn);
                break;
            case AnnotationConstant.Spring:
                System.out.println(AnnotationConstant.Spring);
                break;
            case AnnotationConstant.Summer:
                System.out.println(AnnotationConstant.Summer);
                break;
            case AnnotationConstant.Winter:
                System.out.println(AnnotationConstant.Winter);
                break;
        }

    }

    /**
     * 测试枚举常量
     * @param enumConstant
     */
    public static void testAnnotation(EnumConstant enumConstant) {

        switch (enumConstant) {
            case Antumn:
                System.out.println(Antumn);
                break;
            case Spring:
                System.out.println(EnumConstant.Spring);
                break;
            case Summer:
                System.out.println(EnumConstant.Summer);
                break;
            case Winter:
                System.out.println(EnumConstant.Winter);
                break;
        }

    }

}
