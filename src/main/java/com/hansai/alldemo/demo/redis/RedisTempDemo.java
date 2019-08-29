package com.hansai.alldemo.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 9:41
 */
public class RedisTempDemo {

    @Autowired
    private RedisTemplate redisTemplate;

    public void test() {
        redisTemplate.opsForList().rightPush("hahaha", "1321346");
    }
}
