package com.jubao.cart.cofig;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(value = MyBatisConfig.class)
public class MapperScannerConfig {
	
	@Bean // mapper接口扫描
	public MapperScannerConfigurer getMapperScannerConfigurer() {

		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

		mapperScannerConfigurer.setBasePackage("com.jubao.cart.mapper");

		return mapperScannerConfigurer;
	}

}
