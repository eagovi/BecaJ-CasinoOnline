package com.casino.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.casino.dataService.DameConexion;

public class Consultas {
	private static final Consultas instancia;
	
	static {
		instancia = new Consultas();
	}
	
	private Consultas() {
		
	}
	
	private Connection pedirConexion() throws SQLException {
		return DameConexion.getInstancia().getConexion();
	}
	
	public static synchronized Consultas getInstancia() {
		return instancia;
	}

}
