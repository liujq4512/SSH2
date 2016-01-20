package com.JNDI;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.JNDI.DBService;

public class JNDIContainer {
	private Context ctx = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() throws Exception{
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		env.put(Context.PROVIDER_URL, "file:/E:/JNDITemp");
		
		ctx = new InitialContext(env);
		loadServices();
	}

	public void loadServices() throws Exception{
		InputStream in=getClass().getResourceAsStream("JNDIContainer.properties");
		  Properties props=new Properties();
		  props.load(in);
		  
		  //inject dbservice
		  String s=props.getProperty("DBServiceClass");
		  Object obj=Class.forName(s).newInstance();
		  if(obj instanceof DBService){
		   DBService db=(DBService)obj;
		   String[] ss=props.getProperty("DBServiceProperty").split(";");
		   for(int i=0;i<ss.length;i++)
		    db.setProperty(i, ss[i]);
		   ctx.rebind(props.getProperty("DBServiceName"), db);
		  }
	}
	
	 public void close() throws NamingException{
		  ctx.close();
	 }
		 
	 public Context getContext(){
		 return ctx;
	 }
	
	
}
