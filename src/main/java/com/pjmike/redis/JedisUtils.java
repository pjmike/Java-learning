package com.pjmike.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author pjmike
 * @create 2018-11-03 16:12
 */
public class JedisUtils {
    private static final JedisUtils jedisutils = new JedisUtils();

    public static JedisUtils getInstance() {
        return jedisutils;
    }

    public JedisPool getPool(String ip, Integer port) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(RedisConfig.MAX_IDLE);
        jedisPoolConfig.setMaxTotal(RedisConfig.MAX_ACTIVE);
        jedisPoolConfig.setMaxWaitMillis(RedisConfig.MAX_WAIT);
        //向连接池借用时是否做连接有效性检测(ping),无效连接将会被移除
        jedisPoolConfig.setTestOnBorrow(true);
        // 向连接池归还时做连接有效性检测(ping),无效连接将会被移除
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool pool = new JedisPool(jedisPoolConfig, ip, port,RedisConfig.TIMEOUT,RedisConfig.PASSWORD);
        return pool;
    }

    public Jedis getJedis(String ip, Integer port) {
        Jedis jedis = null;
        int count = 0;
        while (jedis == null && count < RedisConfig.RETRY_NUM) {
            try {
                jedis = getInstance().getPool(ip, port).getResource();
            } catch (Exception e) {
                System.out.println("get redis failed: "+e.getMessage());
            }
            count++;
        }
        return jedis;
    }

    public void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Pipeline pipeline = jedis.pipelined();
//        pipeline.set("hello", "world");
//        pipeline.incr("counter");
//        System.out.println("还没执行命令");
//        Thread.sleep(100000);
//        System.out.println("这里才开始执行");
//        pipeline.sync();

//        String key = "hello";
//        String script = "return redis.call('get',KEYS[1])";
//        Object object = jedis.eval(script, 1, key);
//        System.out.println(object);
        for (int i = 0; i < 500000; i++) {

            Jedis jedis = JedisUtils.getInstance().getJedis("39.106.63.214", 6379);
            System.out.println(jedis.set("hello"+i, String.valueOf(i)));
        }
    }
}
