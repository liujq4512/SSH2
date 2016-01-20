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
		System.out.println("方法执行之前触发！");
	}
	@AfterReturning(returning="rt",pointcut="execution(* com.action.*.findPerson(..))")
	public void afterReturnMethod(Object rt){//只有匹配返回此类型的目标方法才做增强处理
		System.out.println("方法正常返回之前触发，返回值为："+ rt);
	}
	
	@AfterThrowing(throwing="ex",pointcut="execution(* com.action.*.findPerson(..))")
	public void afterThrowingMethod(Throwable ex){
		System.out.println("方法抛出异常触发，异常信息为:"+ex);
	}
	
	@After("execution(* com.action.*.findPerson(..))")
	public void afterMethod(){
		System.out.println("方法结束后释放资源");
	}
	
	@Around("execution(* com.action.PersonAction.findPerson())")
	public Object aroundMethod(ProceedingJoinPoint jp){
		System.out.println("环绕通知在方法调用之前！");
		Object rt = null;
		try {
			rt = jp.proceed();
			System.out.println("环绕通知在方法执行之后调用");
			return rt;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
}
