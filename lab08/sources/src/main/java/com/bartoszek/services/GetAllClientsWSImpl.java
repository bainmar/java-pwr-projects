package com.bartoszek.services;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.bartoszek.DB;
import com.bartoszek.dao.ClientDAOImpl;
import com.bartoszek.dto.Client;

@WebService
public class GetAllClientsWSImpl {
	
	@WebMethod	
	public List<Client>getAllClients(){
		ClientDAOImpl clientDAOImpl=null;
		try {
			clientDAOImpl = new ClientDAOImpl(DB.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Client> clients= (List<Client>) clientDAOImpl.getAllClients();
		return clients;
	}
	
}
