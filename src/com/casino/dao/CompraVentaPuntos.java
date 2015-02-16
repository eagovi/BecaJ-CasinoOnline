package com.casino.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.casino.dataService.DameConexion;

public class CompraVentaPuntos {
		
	private static final CompraVentaPuntos instancia;
	
	static {
		instancia = new CompraVentaPuntos();
	}
	
	private CompraVentaPuntos() {
		
	}
	
	private Connection pedirConexion() {
		return DameConexion.getInstancia().getConexion();
	}
	
	public static synchronized CompraVentaPuntos getInstancia() {
		return instancia;
	}
	
	public int dameIdTransaccion(String login) {
		try {
			
			Statement oStmt = pedirConexion().createStatement();
			ResultSet rs = oStmt.executeQuery("SELECT id_transaccion FROM Caja WHERE login = '"+login+"'");
			rs.next();
			return Integer.parseInt(rs.getString("id_transaccion"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void sumarPuntos(String login, int id_transaccion, int puntos) {
		try {
			Statement oStmt = pedirConexion().createStatement();
			String sSQL = "INSERT INTO Compra_Puntos " +
					"(id_transaccion, fecha, puntos) " +
					"VALUES ("+id_transaccion+", sysdate, "+puntos+")";
			
			oStmt.executeUpdate(sSQL);
			
			sSQL = "UPDATE Cuenta " +
					"SET puntos = puntos + "+puntos +
					"WHERE login = '"+login+"'";
			
			oStmt.executeUpdate(sSQL);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void restarPuntos(String login, int id_transaccion, int puntos) {
		try {
			Statement oStmt = pedirConexion().createStatement();
			String sSQL = "INSERT INTO Venta_Puntos " +
					"(id_transaccion, fecha, puntos) " +
					"VALUES ("+id_transaccion+", sysdate, "+puntos+")";
			
			oStmt.executeUpdate(sSQL);
			
			sSQL = "UPDATE Cuenta " +
					"SET puntos = puntos - "+puntos +
					"WHERE login = '"+login+"'";
			
			oStmt.executeUpdate(sSQL);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
