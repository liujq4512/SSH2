package com.spring.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspectJ {
	
	@Before("execution(* com.action.*.findPerson(..))")
	public void beforeMethod(){
		System.out.println("����ִ��֮ǰ������");
	}
	@AfterReturning(returning="rt",pointcut="execution(* com.action.*.findPerson(..))")
	public void afterReturnMethod(Object rt){//ֻ��ƥ�䷵�ش����͵�Ŀ�귽��������ǿ����
		System.out.println("������������֮ǰ����������ֵΪ��"+ rt);
	}
	
	@AfterThrowing(throwing="ex",pointcut="execution(* com.action.*.findPerson(..))")
	public void afterThrowingMethod(Throwable ex){
		System.out.println("�����׳��쳣�������쳣��ϢΪ:"+ex);
	}
	
	@After("execution(* com.action.*.findPerson(..))")
	public void afterMethod(){
		System.out.println("�����������ͷ���Դ");
	}
	
	@Around("execution(* com.action.PersonAction.findPerson())")
	public Object aroundMethod(ProceedingJoinPoint jp){
		System.out.println("����֪ͨ�ڷ�������֮ǰ��");
		Object rt = null;
		try {
			rt = jp.proceed();
			System.out.println("����֪ͨ�ڷ���ִ��֮�����");
			return rt;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
}
