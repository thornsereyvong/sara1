package com.balancika.crm.utilities;

import java.util.Base64;



public class GenerateHeader {

	public static void main(String[] args) {
		
		System.out.println(Base64.getUrlEncoder().encodeToString("vichet:vichet@123".getBytes()));
		
		
		
	}

}
