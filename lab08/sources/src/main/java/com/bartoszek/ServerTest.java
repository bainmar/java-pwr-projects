package com.bartoszek;
import javax.xml.ws.Endpoint;

import com.bartoszek.services.EndpointsWSImpl;







public class ServerTest {
	public static void main(String[] args) {		
		//Endpoint.publish("http://localhost:9001/helloWorld", new HelloWorldWSImpl()); //
		Endpoint.publish("http://localhost:9001/addClient", new EndpointsWSImpl());
		//Endpoint.publish("http://localhost:9001/getAllClients", new GetAllClientsWSImpl());
		System.out.println("Done!");
		}
}
