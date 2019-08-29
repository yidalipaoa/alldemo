package com.hansai.alldemo.demo.javase.lambda;


import com.hansai.alldemo.demo.javase.lambda.finterface.TestFunction;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/12
 * @time: 11:17
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3","4","5","6");
        StringBuilder stringBuilder = new StringBuilder();
        /*示例：使用Lmabda表达式*/
        list.forEach((str)-> stringBuilder.append(str).append(";"));
        System.out.println(stringBuilder.toString());

        /*以前的实现方式，new一个接口，并实现其逻辑*/
        new Test().test(stringBuilder.toString(), new TestFunction() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
        /*使用Lmabda表达式*/
        new Test().test(stringBuilder.toString(),str-> System.out.println(str));
    }

    public void test(String data,TestFunction function) {
        System.out.println("这里是函数式接口");
        function.accept(data);
    }
}
