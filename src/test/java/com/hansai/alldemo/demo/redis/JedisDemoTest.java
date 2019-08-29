package com.hansai.alldemo.demo.redis;

import org.junit.Test;

import static org.junit.Assert.*;

public class JedisDemoTest {
    JedisDemo jedisDemo = new JedisDemo();

    @Test
    public void testString() throws Exception {
        jedisDemo.testString();
    }

    @Test
    public void testCluster() throws Exception {
        jedisDemo.testCluster();
    }

}