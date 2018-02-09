package com.vispractice.license.create;



public class licenseCreateTest {
	public static void main(String[] args){
		CreateLicense cLicense = new CreateLicense();
		
		cLicense.setParam("C:/license/param.properties");
		//
		cLicense.create();
	}
}
