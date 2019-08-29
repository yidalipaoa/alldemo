package com.hansai.alldemo.demo.javase.thread.lock;

/**
 * <p>Description: synchronized 测试</p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 14:00
 */
public class SyncTest {

    Integer status=0;

    /**
     * 对象锁
     * 加锁的级别是具体的实例对象，当多个线程并发访问该对象的同步方法、同步代码块时，会进行同步。
     */
    public synchronized void syncMethod() {
        System.out.println(Thread.currentThread().getId()+"线程进入");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 类锁
     * 当使用synchronized修饰类静态方法时，那么当前加锁的级别就是类，
     * 当多个线程并发访问该类（所有实例对象）的同步方法以及同步代码块时，会进行同步。
     */
    public synchronized static void syncClass() {

    }

    /**
     * 代码块
     * synchronized（X）中配置的x对象实例，当多个线程并发访问该对象的同步方法、同步代码块以及当前的代码块时，会进行同步。
     * 使用同步代码块时要注意的是不要使用String类型对象，因为String常量池的存在，所以很容易导致出问题。
     */
    public void syncCodeBlock() {
        System.out.println(Thread.currentThread().getId()+"进入同步代码块外");
        synchronized (status) {
            System.out.println(Thread.currentThread().getId()+"线程进入同步代码块内");
            status++;
            System.out.println("status:"+status);
        }
    }




}
