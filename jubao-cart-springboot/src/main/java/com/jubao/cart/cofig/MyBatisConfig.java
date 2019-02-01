package com.jubao.cart.cofig;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class MyBatisConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	@ConditionalOnMissingBean //当容器里没有指定的Bean的情况下创建该对象
	public SqlSessionFactoryBean getSessionFactoryBean() {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		// 设置数据源
		sessionFactoryBean.setDataSource(dataSource);
		
		// 设置mybatis的主配置文件
		ResourcePatternResolver resourcePatternResolver=new PathMatchingResourcePatternResolver();
		Resource mybatisxml= resourcePatternResolver.getResource("classpath:mybatis/SqlMapConfig.xml");
		sessionFactoryBean.setConfigLocation(mybatisxml);
		 
		return sessionFactoryBean;
	}

}
