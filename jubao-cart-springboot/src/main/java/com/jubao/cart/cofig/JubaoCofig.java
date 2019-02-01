package com.jubao.cart.cofig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@PropertySource(value = { "classpath:resources/jdbc.properties","classpath:resources/resource.properties"},ignoreResourceNotFound = true)
@ComponentScan(value="com.jubao.cart")
@SpringBootApplication
public class JubaoCofig extends SpringBootServletInitializer {

	 	@Value("${url}")
	    private String jdbcUrl;

	    @Value("${driverClassName}")
	    private String jdbcDriverClassName;

	    @Value("${uname}")
	    private String jdbcUsername;

	    @Value("${password}")
	    private String jdbcPassword;

	    @Bean(destroyMethod = "close")
	    public DataSource dataSource() {
	    	DruidDataSource boneCPDataSource = new DruidDataSource();
	        // 数据库驱动
	        boneCPDataSource.setDriverClassName(jdbcDriverClassName);
	        System.out.println(jdbcDriverClassName+"------"+jdbcUsername+"----"+jdbcPassword+"-------"+jdbcUrl);
	        // 相应驱动的jdbcUrl
	        boneCPDataSource.setUrl(jdbcUrl);
	        // 数据库的用户名
	        boneCPDataSource.setUsername(jdbcUsername);
	        // 数据库的密码
	        boneCPDataSource.setPassword(jdbcPassword);
	      /*  boneCPDataSource.setInitialSize(10);
	        // 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
	        boneCPDataSource.setRemoveAbandonedTimeout(60);
	        // 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
	        boneCPDataSource.setRemoveAbandoned(true);
	        // 每个分区最大的连接数
	        boneCPDataSource.setMaxActive(100);
	        // 每个分区最小的连接数
	        boneCPDataSource.setMinIdle(5);*/
	        return boneCPDataSource;
	}
	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(JubaoCofig.class);
	    }

}
