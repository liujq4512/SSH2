package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Person;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.service.PersonService;

public class PersonAction {
	@Resource
	private PersonService personService;
	private List<Person> persons;
	
	
	public List<Person> getPersons() {
		return persons;
	}


	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public String findPerson(){
		//String welc = this.getText("successLogin", "liujq");
		//System.out.println(welc);
		
		//添加一个在视图跳转之前的监听事件
		ActionInvocation invocation = ActionContext.getContext().getActionInvocation();
		invocation.addPreResultListener(new PreResultListener(){    
            public void beforeResult(ActionInvocation action,String resultCode){    
                System.out.println("before dispatcher to the view in: " +this.getClass().getName()); 
                
                }    
        });
		this.persons = this.personService.findAllPerson();
		return "list";
	}
	public String insertPerson(){
		Person p = new Person();
		p.setAge("24");
		p.setName("liujq");
		this.personService.savePerson(p);
		return "success";
	}
}
