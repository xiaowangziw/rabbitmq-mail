package com.wxf.rabbitmq.mail.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

@Component
@Slf4j
public class JedisUtil {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    @Autowired
    private JedisPool jedisPool;

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 设值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key: {} value: {} error", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public  boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        Jedis jedis = null;
        String result = "";
        try {
            jedis = getJedis();
            result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        } catch (Exception e) {
            log.error("定时任务获取锁失败" + e.getMessage());
        }
        return LOCK_SUCCESS.equals(result);

    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public  boolean releaseDistributedLock(String lockKey, String requestId) {
        Jedis jedis  = getJedis();;
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        return RELEASE_SUCCESS.equals(result);
    }

    /**
     * 设值
     *
     * @param key
     * @param value
     * @param expireTime 过期时间, 单位: s
     * @return
     */
    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} expireTime:{} error", key, value, expireTime, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 设值
     *
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            log.error("exists key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 设值key过期时间
     *
     * @param key
     * @param expireTime 过期时间, 单位: s
     * @return
     */
    public Long expire(String key, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key.getBytes(), expireTime);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    /**
     * 获取剩余时间
     *
     * @param key
     * @return
     */
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

}
