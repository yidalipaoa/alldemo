package com.hansai.alldemo.demo.javase.thread.lock;

import org.junit.Test;

public class VolatileTestTest {
    @Test
    public void volaTest() throws Exception {
        VolatileTest volatileTest = new VolatileTest();
        for(int i=0;i<1000;i++){
            new Thread(volatileTest::volaTest).start();
        }
        Thread.sleep(30000);
    }

}