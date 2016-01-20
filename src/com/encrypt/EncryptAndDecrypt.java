package com.encrypt;

import java.io.UnsupportedEncodingException;

public class EncryptAndDecrypt {

	public static void main(String[] args){
		String s = "the message not encrypt !";
		String key = "keyValue";
		
		s = encrypt(s,key);
		
		System.out.println(s);
		
		s = decrypt(s,key);
		
		System.out.println(s);

	}
	//加密过程
	public static String encrypt(String s,String key){
		byte[] keyByte;
		byte[] bytes ;
		try {
			keyByte = key.getBytes("UTF-8");
			bytes = s.getBytes("UTF-8");
			
			for(int i=0;i<bytes.length ; i++){
				for(byte k:keyByte){
					bytes[i] = (byte) (bytes[i]^k);
				}
			}
			return new String(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//解密过程
	public static String decrypt(String s,String key){
		
		byte[] keyByte;
		byte[] bytes ;
		try {
			keyByte = key.getBytes("UTF-8");
			bytes = s.getBytes("UTF-8");
			
			for(int i=0;i<bytes.length ; i++){
				for(byte k:keyByte){
					bytes[i] = (byte) (bytes[i]^k);
				}
			}
			return new String(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
