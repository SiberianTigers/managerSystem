package com.jubao.rest.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jubao.pojo.TemplateValue;
import com.jubao.rest.service.ItemService;

public class myTest {
	
	
	
	@Test
	public void test(){
  
		 ApplicationContext app=new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
		
		   ItemService itemService=(ItemService)app.getBean("itemService");
		 
		   TemplateValue templateValue=itemService.findByIdTemplateValue(154091067188402L);
	  
		   System.out.println(templateValue.getParamData());
	}

}
