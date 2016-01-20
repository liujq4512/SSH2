package com.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.encrypt.EncryptAndDecrypt;





public class SimpleClient {

	public static void main( String[] args ) throws IOException {
        HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口
        // client.getHostConfiguration().setProxy( "172.26.184.189", 80 );
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod method = new GetMethod( "http://192.168.15.132:8081/bpp-center/DBInfo/decrypt/getDBInfo?jndiName=oracle_billdb_mas_29" );
 
        // 这里设置字符编码，避免乱码
        method.setRequestHeader( "Content-Type", "text/html;charset=utf-8" );
        
        client.executeMethod( method );
        // 打印服务器返回的状态
        System.out.println( method.getStatusLine() );
 
        // 获取返回的html页面
        byte[] body = method.getResponseBody();
        // 打印返回的信息
        System.out.println( new String( body, "utf-8" ) );
        
        String s = EncryptAndDecrypt.decrypt(new String( body, "utf-8" ), "keyValue");
        System.out.println(s);
        // 释放连接
        method.releaseConnection();
    }
}
