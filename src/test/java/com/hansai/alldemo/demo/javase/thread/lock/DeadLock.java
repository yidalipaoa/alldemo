package com.hansai.alldemo.demo.javase.thread.lock;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 16:43
 */
public class DeadLock {

    private static long sum() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }

    public static void main(String[] args) {
//        long starttime = System.currentTimeMillis();
//        DeadLock.sum();
//        long endtime = System.currentTimeMillis();
//        System.out.println(endtime-starttime);
        System.out.println(77 | 55);

    }
}
