package com.framework.core.utils;

import java.util.UUID;

public class GetGuId {
	public String getGuId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		GetGuId guid = new GetGuId();
		System.out.println(guid.getGuId());
	}
}
