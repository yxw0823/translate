package com.framework.core.exception;


public class ActionException extends Exception implements MessageAlertable,Logable{
	private static final long serialVersionUID = 1L;
   
	public ActionException(String msg,Throwable e){
		super(msg, e);
	}
	public ActionException(String msg){
		super(msg);
	}
}
