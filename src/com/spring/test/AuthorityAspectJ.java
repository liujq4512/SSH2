package com.spring.test;

import org.aspectj.lang.ProceedingJoinPoint;

public class AuthorityAspectJ {
	
	public void beforeMethod(){
		System.out.println("方法执行之前触发权限检查！");
	}
	public void afterReturnMethod(Object rt){//只有匹配返回此类型的目标方法才做增强处理
		System.out.println("方法正常返回之前触发权限检查，返回值为："+ rt);
	}
	
	public void afterThrowingMethod(Throwable ex){
		System.out.println("方法抛出异常触发权限检查，异常信息为:"+ex);
	}
	
	public void afterMethod(){
		System.out.println("方法结束后权限检查");
	}
	
	public Object aroundMethod(ProceedingJoinPoint jp){
		System.out.println(jp.getSignature().getName()+"环绕通知在方法调用之前权限检查！");
		Object rt = null;
		try {
			rt = jp.proceed();
			System.out.println(jp.getSignature().getName()+"环绕通知在方法执行之后权限检查");
			return rt;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
}
