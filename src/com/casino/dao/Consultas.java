package com.casino.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public int numeroCuentas(int tipo) {
		try {
			Connection conexion = pedirConexion();	
			Statement oStmt = conexion.createStatement();
			
			ResultSet rs = oStmt.executeQuery("SELECT count(1) as cuenta "+ 
					"FROM Cuenta WHERE tipo_cuenta = "+tipo);
			rs.next();
			
			int total = Integer.parseInt(rs.getString("cuenta"));
			
			oStmt.close();
			conexion.close();
			
			return total;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int numeroPartidas(int juego) {
		try {
			Connection conexion = pedirConexion();	
			Statement oStmt = conexion.createStatement();
			
			ResultSet rs = oStmt.executeQuery("SELECT count(1) as cuenta "
												+"FROM Balance WHERE id_juego="+juego);
			rs.next();
			
			int total = Integer.parseInt(rs.getString("cuenta"));
			
			oStmt.close();
			conexion.close();
			
			return total;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int totalUsuarios() {
		try {
			Connection conexion = pedirConexion();	
			Statement oStmt = conexion.createStatement();
			
			ResultSet rs = oStmt.executeQuery("SELECT count(1) as cuenta "
												+ "FROM Cliente c, Usuario u "
												+ "WHERE c.login = u.login AND borrar = 0 AND tipo_user = 1");
			rs.next();
			
			int total = Integer.parseInt(rs.getString("cuenta"));
			
			oStmt.close();
			conexion.close();
			
			return total;
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
