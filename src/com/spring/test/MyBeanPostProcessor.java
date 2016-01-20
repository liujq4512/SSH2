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
		System.out.println("bean �������ڳ�ʼ��֮���"+ beanName +"���д���");
		//���ص��Ǵ�����bean ʵ������bean����������ʵ���õ���bean
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("bean �������ڳ�ʼ��֮ǰ��"+ beanName + " ���д���");
		return bean;
	}

}
