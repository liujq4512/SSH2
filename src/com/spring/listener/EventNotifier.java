package com.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EventNotifier implements ApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof PersonEvent){
			PersonEvent evt = (PersonEvent)event;
			System.out.println(evt.getName()+"!!!!!!!!!!!!!!!");
		}else{
			System.out.println(event.getSource()+"===========");
		}
		
	}

}
