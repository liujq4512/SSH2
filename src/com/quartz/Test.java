package com.quartz;

import java.util.Date;


 	









import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class Test {
	private static final SimpleTrigger CronTrigger = null;  
	
	public static void main(String[] args) {
		
		try {
			simpleTrigger();
			triggerJob();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void simpleTrigger(){
		SchedulerFactory schedulerF = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try{
			scheduler = schedulerF.getScheduler();
			JobDetail detail = JobBuilder.newJob(TestJob.class).withIdentity("testJob", "jobGrop").build();
			
//	      使用simpleTrigger规则  
//	        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")  
//	                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(8))  
//	                        .startNow().build();  
//	      使用cornTrigger规则  
	       Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup").withSchedule(CronScheduleBuilder.cronSchedule("12 * * * * ? *")).startNow().build(); 
			
	       scheduler.scheduleJob(detail, trigger);
	       scheduler.start();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
	public static void triggerJob(){
		SchedulerFactory schedulerF = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try{
			scheduler = schedulerF.getScheduler();
			//scheduler.pauseJob(JobKey.jobKey("testJob", "jobGrop"));
			
			List<String> list = scheduler.getJobGroupNames();
			for(String s : list){
				System.out.println(s);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
