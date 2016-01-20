package com.spring.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spring.test.GetSpringContext;
import com.spring.test.MyBeanPostProcessor;

public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "l";
		change(s);
		System.out.println(s);
		start();
	}
	
	public static void change(String ss ){
		ss = "ss";
	}
	public static void start(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		//ApplicationContext ctx = new FileSystemXmlApplicationContext("src/beans.xml");
		//����һ��ApplicationEvent ����
		PersonEvent evt = new PersonEvent("person");
		//�������������¼�
		ctx.publishEvent(evt);
		
		//�Ƚ����ַ�����ȡ��beanfactory �Ƿ���ͬ
		GetSpringContext getctx = (GetSpringContext) ctx.getBean("springContext");
		System.out.println(getctx.getContext());
		System.out.println(ctx.equals(getctx.getContext()));
		
		System.out.println("----------"+java.util.Arrays.toString(ctx.getBeanDefinitionNames()));
	}
	
}
