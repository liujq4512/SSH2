package com.spring.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.interceptor.MyMethodInterceptor;

public class MyBeanPostProcessor implements BeanPostProcessor{
	private final Log log = LogFactory.getLog(MyBeanPostProcessor.class);
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(log.isErrorEnabled()){
			log.error("//////////////////////////INFO: postProcessAfterInitialization////////////");
		}
		System.out.println("bean 后处理器在初始化之后对"+ beanName +"进行处理");
		//返回的是处理后的bean 实例，该bean就是容器中实际用到的bean
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("bean 后处理器在初始化之前对"+ beanName + " 进行处理");
		return bean;
	}

}
