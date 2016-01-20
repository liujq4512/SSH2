package com.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		String ip = "asdfasdf192.168.15.132llllll192.168.15.112dfasd";
		String regex = "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}";   
		
		String regex1 = "fld";
		
        Pattern pattern = Pattern.compile(regex);   
        Matcher matcher = pattern.matcher(ip);
		
       // System.out.println("共找到 : " + matcher.groupCount() + " 处符合！");
        while(matcher.find()){
        	System.out.println("=====" + matcher.group());
        }
        
        
	}

}
