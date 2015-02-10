package com.casino.modelo.logica.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.casino.dataService.DameConexion;

/**
 * Servlet implementation class ComprarPuntos
 */
@WebServlet("/ComprarPuntos")
public class ComprarPuntos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarPuntos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		if(session != null) {
			
			String login = (String) session.getAttribute("nombre");
			
			DameConexion instancia = DameConexion.getInstancia();
			
			Statement oStmt = null;
			String sSQL = null;
			String sSQLIdCompra = null;
			Connection miConexion=null;
			ResultSet rs = null;
			int id_compra = 0;
			String puntos = request.getParameter("puntos");
			
			sSQLIdCompra = "SELECT id_transaccion FROM Caja WHERE login = '"+login+"'";
			
			try {
				miConexion = instancia.getConexion();
				oStmt = miConexion.createStatement();
			
				rs = oStmt.executeQuery(sSQLIdCompra);
				rs.next();
				id_compra = Integer.parseInt(rs.getString("id_transaccion"));
				
				sSQL = "INSERT INTO Compra_Puntos " +
						"(id_transaccion, fecha, puntos) " +
						"VALUES ("+id_compra+", sysdate, "+puntos+")";
				
				oStmt.executeUpdate(sSQL);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}					
		}
	}

}
