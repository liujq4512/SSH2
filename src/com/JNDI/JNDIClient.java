package com.JNDI;

import javax.naming.Context;



public class JNDIClient {
	
	public static void main(String[] args){
		JNDIContainer container = new JNDIContainer();
		try {
			container.init();
			Context ctx = container.getContext();
			
			DBService db=(DBService)ctx.lookup("DBService");
		    System.out.println("db location is:"+db.getLocation()+",state is:"+db.getState());
		    db.accessDB();
		    container.close();
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
