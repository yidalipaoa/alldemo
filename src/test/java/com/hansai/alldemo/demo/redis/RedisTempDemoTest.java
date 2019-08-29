package com.hansai.alldemo.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTempDemoTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testString() {
        stringRedisTemplate.opsForValue().set("测试1","23424322432");
        stringRedisTemplate.opsForValue().get("测试1");
        stringRedisTemplate.opsForValue().set("测试","",300, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate);
    }

    @Test
    public void testList() throws Exception {

    }

}