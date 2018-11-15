package com.jubao.rest.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	
	// 连接redis集群
		@Test
		public void testJedisCluster() {

			JedisPoolConfig config = new JedisPoolConfig();
			// 最大连接数
			config.setMaxTotal(30);
			// 最大连接空闲数
			config.setMaxIdle(2);

			//集群结点
			Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
			jedisClusterNode.add(new HostAndPort("119.29.195.240", 7001));
			jedisClusterNode.add(new HostAndPort("119.29.195.240", 7002));
			jedisClusterNode.add(new HostAndPort("119.29.195.240", 7003));
			jedisClusterNode.add(new HostAndPort("47.107.36.50", 7004));
			jedisClusterNode.add(new HostAndPort("47.107.36.50", 7005));
			jedisClusterNode.add(new HostAndPort("47.107.36.50", 7006));
			JedisCluster jc = new JedisCluster(jedisClusterNode, config);
			
			JedisCluster jcd = new JedisCluster(jedisClusterNode);
			jcd.set("name", "zhangsan");
			String value = jcd.get("name");
			System.out.println(value);
		}
	
}
