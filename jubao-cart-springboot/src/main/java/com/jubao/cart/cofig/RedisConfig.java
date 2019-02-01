package com.jubao.cart.cofig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
/***
 * redis集群
 * @author 12146
 *
 */
@Configuration
@PropertySource("classpath:resources/redis.properties")
public class RedisConfig {

	@Value("${config.maxTotal}")
	private Integer maxTotal;
	@Value("${config.maxIdle}")
	private Integer maxIdle;
	@Value("${config.numTestsPerEvictionRun}")
	private Integer numTestsPerEvictionRun;
	@Value("${config.timeBetweenEvictionRunsMillis}")
	private Integer timeBetweenEvictionRunsMillis;
	@Value("${config.minEvictableIdleTimeMillis}")
	private Integer minEvictableIdleTimeMillis;
	@Value("${config.softMinEvictableIdleTimeMillis}")
	private Integer softMinEvictableIdleTimeMillis;
	@Value("${config.maxWaitMillis}")
	private Integer maxWaitMillis;
	@Value("${config.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${config.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${config.blockWhenExhausted}")
	private boolean blockWhenExhausted;

	@Value("${host.ip1}")
	private String hostName1;
	@Value("${host.prot1}")
	private Integer prot1;

	@Value("${host.ip2}")
	private String hostName2;
	@Value("${host.prot2}")
	private Integer prot2;

	@Value("${host.ip3}")
	private String hostName3;
	@Value("${host.prot3}")
	private Integer prot3;

	@Value("${host.ip4}")
	private String hostName4;
	@Value("${host.prot4}")
	private Integer prot4;

	@Value("${host.ip5}")
	private String hostName5;
	@Value("${host.prot5}")
	private Integer prot5;

	@Value("${host.ip6}")
	private String hostName6;
	@Value("${host.prot6}")
	private Integer prot6;

	@Value("${cluster.timeout}")
	private Integer timeout;
	@Value("${cluster.maxRedirections}")
	private Integer maxRedirections;

	private JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestWhileIdle(testWhileIdle);
		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		return jedisPoolConfig;
	}

	@Bean
	public JedisCluster getJedisCluster() {
		Set<HostAndPort> sethost = new HashSet<HostAndPort>();
		sethost.add(new HostAndPort(hostName1, prot1));
		sethost.add(new HostAndPort(hostName2, prot2));
		sethost.add(new HostAndPort(hostName3, prot3));
		sethost.add(new HostAndPort(hostName4, prot4));
		sethost.add(new HostAndPort(hostName5, prot5));
		sethost.add(new HostAndPort(hostName6, prot6));
		
		JedisCluster jedisCluster = new JedisCluster(sethost, timeout, maxRedirections, jedisPoolConfig());
		return jedisCluster;
	}
	
	

}
