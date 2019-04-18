package it.polito.tdp.poweroutages.db;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.Nerc;

public class TestConnection {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;
			System.out.println(dao.getEventList(new Nerc (3,"MAAC"))); 

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
	}

}
