package com.casino.dataService;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DameConexion {

	private static final DameConexion instancia;
	private Connection conexion;
	static {
		instancia = new DameConexion();
	}
	
	private DameConexion() {		
		try {
			Context ic = new InitialContext();
			DataSource miDS = (DataSource) ic.lookup("java:comp/env/jdbc/DataSourceLocal1");
			setConexion(miDS.getConnection());
			
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}
	
	public static synchronized DameConexion getInstancia() {
		return instancia;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
