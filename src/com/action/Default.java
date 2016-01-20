package com.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionContext;

public class Default {
	
	public String post(){
		return "showTips";
	}
	public void json(){
		this.test();
		
		JSONObject obj = new JSONObject();
		JSONArray json = new JSONArray();
		for(int i=0;i<4;i++){
			JSONObject obj1 = new JSONObject();
			obj1.put("name", "name"+i);
			obj1.put("value", "value"+i);
			json.add(obj1);
		}
		//两种获取servlet api 方法
//		ActionContext ctx = ActionContext.getContext();
//		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/x-json");
		try {
			PrintWriter pt = response.getWriter();
			pt.write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test(){
		System.out.println("test method");
	}
	
	public void receiveFromOtherSystem(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			BufferedReader r = request.getReader();
			StringBuffer buf = new StringBuffer();
			String s = null;
			s = r.readLine();
			while((s=r.readLine())!=null){
				buf.append(s);
			}
			System.out.println(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
