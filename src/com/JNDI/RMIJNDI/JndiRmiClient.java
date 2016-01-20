package com.JNDI.RMIJNDI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class JndiRmiClient {

	public static void main(String[] args) {
		//设置JNDI属性
        Properties properties = new Properties();
        //RMI的JNDI工厂类
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY , "com.sun.jndi.rmi.registry.RegistryContextFactory");
        //RMI服务端的访问地址
        properties.setProperty(Context.PROVIDER_URL, "rmi://localhost:8080");
        try {
        	//根据JNDI属性，创建上下文
            InitialContext ctx = new InitialContext(properties);
            //根据JNDI上下文，查找并获取到远程的RMI对象
            RmiSimple remote = (RmiSimple) ctx.lookup("java:com/bill99/JNDI/RMIJNDI/RmiSimple");
            RmiSimple test = (RmiSimple) ctx.lookup("test");
            System.out.println(remote.sayHello()  + "    " + test.sayHello());
            
//            System.out.println(ctx.lookup("RmiSimple"));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
