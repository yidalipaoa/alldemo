package com.hansai.alldemo.demo.javase.thread.lock;

import org.junit.Test;

public class SyncTestTest {
    /**
     * 测试对象锁
     * @throws Exception
     */
    @Test
    public void syncMethod() throws Exception {
        SyncTest syncTest = new SyncTest();
        for(int i=0;i<10;i++){
            new Thread(syncTest::syncMethod).start();
        }
        Thread.sleep(300000);
    }

    /**
     * 测试类锁
     * @throws Exception
     */
    @Test
    public void syncClass() throws Exception {
        SyncTest syncTest = new SyncTest();
        for(int i=0;i<10;i++){
            new Thread(SyncTest::syncClass).start();
        }
        Thread.sleep(30000);
    }

    /**
     * 测试同步代码块
     * @throws Exception
     */
    @Test
    public void syncCodeBlock() throws Exception {
        SyncTest syncTest = new SyncTest();
        for(int i=0;i<100;i++){
            new Thread(syncTest::syncCodeBlock).start();
        }
        Thread.sleep(30000);

    }

}