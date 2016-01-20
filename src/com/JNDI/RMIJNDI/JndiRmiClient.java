package com.JNDI.RMIJNDI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class JndiRmiClient {

	public static void main(String[] args) {
		//����JNDI����
        Properties properties = new Properties();
        //RMI��JNDI������
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY , "com.sun.jndi.rmi.registry.RegistryContextFactory");
        //RMI����˵ķ��ʵ�ַ
        properties.setProperty(Context.PROVIDER_URL, "rmi://localhost:8080");
        try {
        	//����JNDI���ԣ�����������
            InitialContext ctx = new InitialContext(properties);
            //����JNDI�����ģ����Ҳ���ȡ��Զ�̵�RMI����
            RmiSimple remote = (RmiSimple) ctx.lookup("java:com/bill99/JNDI/RMIJNDI/RmiSimple");
            RmiSimple test = (RmiSimple) ctx.lookup("test");
            System.out.println(remote.sayHello()  + "    " + test.sayHello());
            
//            System.out.println(ctx.lookup("RmiSimple"));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
