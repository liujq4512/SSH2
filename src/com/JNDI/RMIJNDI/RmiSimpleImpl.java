package com.JNDI.RMIJNDI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiSimpleImpl extends UnicastRemoteObject implements RmiSimple{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RmiSimpleImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return "hello lili";
	}

}
