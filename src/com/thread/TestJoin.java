package com.thread;

public class TestJoin{
	
	public static void main(String args[]){
		Examples example = new Examples();
		example.start();
		try {
			example.join(2000);//在这里等待example执行指定时间后才往下执行
			System.out.println("===================");
			example.join(3000);//等待example 再执行指定时间
			System.out.println("===================1");
			example.join();//等待example 执行完毕
			System.out.println("example execute finished!");
			
			Thread.sleep(1000);
			System.out.println(example.name);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
class Examples extends Thread{
	String name = "example";
	public void run(){
		for(int i=0; i<10 ; i++){
			try {
				
				System.out.println(i);
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}