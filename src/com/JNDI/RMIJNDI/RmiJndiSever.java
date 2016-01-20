package com.JNDI.RMIJNDI;

import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RmiJndiSever {

	public static void main(String[] args) {
		try {
        	//ע��RMI�������˿�
            LocateRegistry.createRegistry(8080);
            //����RMI����˽ӿ�ʵ�ֶ���
            RmiSimple server = new RmiSimpleImpl();
            //����JNDI����
            Properties properties = new Properties();
            //RMI��JNDI������
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY , "com.sun.jndi.rmi.registry.RegistryContextFactory");
            //RMI����˵ķ��ʵ�ַ
            properties.setProperty(Context.PROVIDER_URL, "rmi://localhost:8080");
            //����JNDI���ԣ�����������
            InitialContext ctx = new InitialContext(properties);
            //������˽ӿ�ʵ�ֶ�����JNDI�����󶨣�����ط�д�Ĳ����Ǻܹ淶
            //�����J2EE�����У��淶��д���ǣ��󶨵�����Ҫ��java:comp/env/��ͷ
            ctx.bind("java:com/bill99/JNDI/RMIJNDI/RmiSimple", server);
            System.out.println("RMI��JNDI���ɷ�������.�ȴ��ͻ��˵���...");
            InitialContext ctx1 = new InitialContext(properties);
            ctx1.bind("test", server);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } 

	}

}
