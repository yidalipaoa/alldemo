package com.hansai.alldemo.demo.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/17
 * @time: 9:27
 */
public class JedisDemo {

    public void testString() {
        Jedis jedis = new Jedis("192.168.203.128",7000);
        jedis.set("testtest","sdfsdf");
        System.out.println(jedis.get("testtest"));
    }

    /**
     * 连接redis集群
     */
    public void testCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.203.128",7000));
        nodes.add(new HostAndPort("192.168.203.128",7001));
        nodes.add(new HostAndPort("192.168.203.128",7002));
        nodes.add(new HostAndPort("192.168.203.128",7003));
        nodes.add(new HostAndPort("192.168.203.128",7004));
        nodes.add(new HostAndPort("192.168.203.128",7005));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        System.out.println(jedisCluster.getClusterNodes().size());
        jedisCluster.set("testclusterjedis", "wfswerwer");
        System.out.println(jedisCluster.get("testclusterjedis"));
        jedisCluster.close();


    }
}
