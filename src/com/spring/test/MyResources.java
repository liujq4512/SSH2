package com.spring.test;

import java.net.MalformedURLException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;

public class MyResources {
	public static void main(String args[]){
		urlResources();
	}
	public static void urlResources(){
		try {
			UrlResource url = new UrlResource("file:D:\\ljq\\documents\\Technical reference documents\\jQuery.pdf");
			ClassPathResource cr = new ClassPathResource("messageResource.properties");
			FileSystemResource fr = new FileSystemResource("D:\\ljq\\documents\\Technical reference documents\\jQuery.pdf");
			System.out.println(url.getDescription());
			System.out.println(url.getFilename());
			System.out.println(cr.getDescription());
			System.out.println(cr.getFilename());
			System.out.println(fr.getDescription());
			System.out.println(fr.getFilename());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
