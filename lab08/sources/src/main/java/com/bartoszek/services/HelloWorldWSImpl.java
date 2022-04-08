package com.bartoszek.services;
import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class HelloWorldWSImpl{
	
	@WebMethod
	public String helloWorld(String name) {
		return "Hello " + name;
	}

}
