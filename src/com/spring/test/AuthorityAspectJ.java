package com.spring.test;

import org.aspectj.lang.ProceedingJoinPoint;

public class AuthorityAspectJ {
	
	public void beforeMethod(){
		System.out.println("����ִ��֮ǰ����Ȩ�޼�飡");
	}
	public void afterReturnMethod(Object rt){//ֻ��ƥ�䷵�ش����͵�Ŀ�귽��������ǿ����
		System.out.println("������������֮ǰ����Ȩ�޼�飬����ֵΪ��"+ rt);
	}
	
	public void afterThrowingMethod(Throwable ex){
		System.out.println("�����׳��쳣����Ȩ�޼�飬�쳣��ϢΪ:"+ex);
	}
	
	public void afterMethod(){
		System.out.println("����������Ȩ�޼��");
	}
	
	public Object aroundMethod(ProceedingJoinPoint jp){
		System.out.println(jp.getSignature().getName()+"����֪ͨ�ڷ�������֮ǰȨ�޼�飡");
		Object rt = null;
		try {
			rt = jp.proceed();
			System.out.println(jp.getSignature().getName()+"����֪ͨ�ڷ���ִ��֮��Ȩ�޼��");
			return rt;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
}
