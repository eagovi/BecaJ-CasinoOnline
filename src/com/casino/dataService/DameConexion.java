package com.casino.dataService;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DameConexion {

	private static final DameConexion instancia;
	private Context ic;
	private DataSource miDS;
	
	static {
		instancia = new DameConexion();
	}
	
	private DameConexion() {		
		try {
			ic = new InitialContext();
			miDS = (DataSource) ic.lookup("java:comp/env/jdbc/DataSourceLocal1");
			
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	}
	
	public static synchronized DameConexion getInstancia() {
		return instancia;
	}

	public Connection getConexion() throws SQLException {
		return miDS.getConnection();
	}
}
