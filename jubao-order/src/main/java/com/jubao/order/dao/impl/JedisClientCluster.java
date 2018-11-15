package com.jubao.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jubao.order.dao.JedisClient;

import redis.clients.jedis.JedisCluster;
/***
 * Jedis集群操作类
 * @author 12146
 *
 */

public class JedisClientCluster implements JedisClient{
	
	@Autowired
	private JedisCluster jedisCluster;
	
	public String get(String key) {
		return jedisCluster.get(key);
	}


	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}
	
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}

	
	public long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}

	public long incr(String key) {
		return jedisCluster.incr(key);
	}

	
	public long expire(String key, int second) {
		return jedisCluster.expire(key, second);
	}


	public long ttl(String key) {
		return jedisCluster.ttl(key);
	}


	@Override
	public long del(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.del(key);
	}


	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		return jedisCluster.hdel(key,key);
	}
	
}
