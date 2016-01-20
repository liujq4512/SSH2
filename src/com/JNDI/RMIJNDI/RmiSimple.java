package com.JNDI.RMIJNDI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiSimple extends Remote{
	
	public String sayHello() throws RemoteException;
}
