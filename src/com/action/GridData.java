package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GridData {
	
	@SuppressWarnings("unchecked")
	public void getData(){
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("id", 1);
		obj.put("name", "1223");
		obj.put("sex", "male");
		obj.put("age", 23);
		JSONObject obj1 = new JSONObject();
		obj1.put("id", 2);
		obj1.put("name", "ss3");
		obj1.put("sex", "female");
		obj1.put("age", 24);
		arr.add(obj);
		arr.add(obj1);
		JSONObject data = new JSONObject();
		data.put("root", arr);
		data.put("totalProperty", 2);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			PrintWriter writer = response.getWriter();
			writer.print(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
