package com.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.encrypt.EncryptAndDecrypt;





public class SimpleClient {

	public static void main( String[] args ) throws IOException {
        HttpClient client = new HttpClient();
        // ���ô����������ַ�Ͷ˿�
        // client.getHostConfiguration().setProxy( "172.26.184.189", 80 );
        // ʹ�� GET ���� �������������Ҫͨ�� HTTPS ���ӣ���ֻ��Ҫ������ URL �е� http ���� https
        HttpMethod method = new GetMethod( "http://192.168.15.132:8081/bpp-center/DBInfo/decrypt/getDBInfo?jndiName=oracle_billdb_mas_29" );
 
        // ���������ַ����룬��������
        method.setRequestHeader( "Content-Type", "text/html;charset=utf-8" );
        
        client.executeMethod( method );
        // ��ӡ���������ص�״̬
        System.out.println( method.getStatusLine() );
 
        // ��ȡ���ص�htmlҳ��
        byte[] body = method.getResponseBody();
        // ��ӡ���ص���Ϣ
        System.out.println( new String( body, "utf-8" ) );
        
        String s = EncryptAndDecrypt.decrypt(new String( body, "utf-8" ), "keyValue");
        System.out.println(s);
        // �ͷ�����
        method.releaseConnection();
    }
}
