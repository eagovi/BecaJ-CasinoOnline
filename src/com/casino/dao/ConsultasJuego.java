package com.casino.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
