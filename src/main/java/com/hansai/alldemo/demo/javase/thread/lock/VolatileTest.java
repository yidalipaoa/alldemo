package com.hansai.alldemo.demo.javase.thread.lock;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 16:07
 */
public class VolatileTest {

    public volatile int status;


    /**
     * 当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值。
     */
    public void volaTest() {
        System.out.println(Thread.currentThread().getId()+"线程进入");
        System.out.println(++status);
    }
}
