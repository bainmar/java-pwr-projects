package com.bartoszek.services;


import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.bartoszek.DB;
import com.bartoszek.dao.ClientDAOImpl;
import com.bartoszek.dto.Client;

@WebService
public class EndpointsWSImpl {
	
	@WebMethod
	public void insertClient(String name, String surname, Integer clientNumber) {
		try {			
			new ClientDAOImpl(DB.getConnection()).insertClient(new Client(name,surname,clientNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
