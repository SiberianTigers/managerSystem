package com.jubao.cart.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jubao.cart.dao.JedisClient;
import com.jubao.cart.utils.Cart;
import com.jubao.cart.utils.EmptyUtils;
import com.jubao.cart.utils.SerializeUtil;
import com.jubao.pojo.Product;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/***
 * Jedis集群操作类
 * 
 * @author 12146
 *
 */

public class JedisClientCluster implements JedisClient {

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
		return jedisCluster.hdel(key, key);
	}

}
