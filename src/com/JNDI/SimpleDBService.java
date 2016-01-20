package com.JNDI;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class SimpleDBService implements Referenceable,DBService{

	private String location="jdbc:oracle:thin:@192.168.15.197:1521:orcl";//���ݿ��������֮һ�����ݿ�λ��
	 private String state="start"; //���ݿ��������֮�������ݿ�״̬
	 
	 public Reference getReference() throws NamingException {
	  //Reference�Ƕ�������ã�Context�д�ŵ���Reference��Ϊ�˴�Reference�л�ԭ������ʵ����
	  //������Ҫ���RefAddr�������Ǵ�������ʵ���������������ǵ�SimpleDBService�У�location��state����������������
	  Reference ref=new Reference(getClass().getName(),SimpleDBServiceFactory.class.getName(),null);
	  ref.add(new StringRefAddr("location",location));
	  ref.add(new StringRefAddr("state",state));
	  return ref;
	 }
	 
	 public void accessDB() {
	  if(state.equals("start"))
	   System.out.println("we are accessing DB.");
	  else
	   System.out.println("DB is not start.");
	 }
	 public String getLocation() {
	  return location;
	 }
	 public String getState() {
	  return state;
	 }
	 
	 public void setProperty(int index,String property){
	  if(index==0)
	   location=property;
	  else
	   state=property;
	 }

}
