package com.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GetSpringContext implements ApplicationContextAware{
	//��beanFactory �Գ�Ա������ʽ����
	private ApplicationContext ctx;
	
	//@param arg0 ����beanʵ����ApplicationContext
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.ctx = arg0;
		
	}
	//��ȡ
	public ApplicationContext getContext(){
		return this.ctx;
	}
}
