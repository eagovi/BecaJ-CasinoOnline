package com.casino.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.casino.dataService.DameConexion;

public class ConsultasJuego {

	private static final ConsultasJuego instancia;
	
	static {
		instancia = new ConsultasJuego();
	}
	
	private ConsultasJuego() {
		
	}
	
	private Connection pedirConexion() {
		return DameConexion.getInstancia().getConexion();
	}
	
	public static synchronized ConsultasJuego getInstancia() {
		return instancia;
	}
	
	public int obtenerBalance(String login) {
		
		try {
			Statement oStmt = pedirConexion().createStatement();
			ResultSet rs = oStmt.executeQuery("SELECT id_balance "+ 
					"FROM Caja WHERE login='"+login+"'");
			rs.next();
			
			return Integer.parseInt(rs.getString("id_balance"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void actualizarPuntosCuenta(int id_balance, String login, int apuesta) {
		Statement oStmt;
		try {
			oStmt = pedirConexion().createStatement();
		
			oStmt.executeUpdate("INSERT INTO balance(id_balance, puntos, fecha, id_juego) "
								+"values("+id_balance+", "+apuesta+", sysdate, 1)");
		
			oStmt.executeUpdate("UPDATE cuenta "+
								"SET puntos = puntos +"+apuesta+
								"WHERE login = '"+login+"'");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Devuelve el nombre la imagen y los puntos del cliente
	public HashMap<String, String> dameInfoCliente(String login) {
		Statement oStmt;
		HashMap<String, String> datos = new HashMap<String, String>();
		try {
			oStmt = pedirConexion().createStatement();
			
			ResultSet rs = oStmt.executeQuery("SELECT c.nombre as nombre, c.imagen as imagen, cu.puntos as puntos "+ 
					"FROM Cliente c, Cuenta cu WHERE c.login = cu.login AND c.login='"+login+"'");
			
			rs.next();
			
			datos.put("nombre", rs.getString("nombre"));
			datos.put("imagen", rs.getString("imagen"));
			datos.put("puntos", rs.getString("puntos"));
			
			return datos;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	public int damePuntosCliente(String login) {
		Statement oStmt;
		
		try {
			oStmt = pedirConexion().createStatement();
			ResultSet rs = oStmt.executeQuery("SELECT puntos "+ 
					"FROM Cuenta WHERE login='"+login+"'");
			
			rs.next();
			return Integer.parseInt(rs.getString("puntos"));
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return -1;
	}
}
