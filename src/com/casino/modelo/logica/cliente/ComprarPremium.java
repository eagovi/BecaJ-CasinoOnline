package com.casino.modelo.logica.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.ResultSet;
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
 * Servlet implementation class ComprarPremium
 */
@WebServlet("/ComprarPremium")
public class ComprarPremium extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarPremium() {
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
			Connection miConexion=null;
			
			try {
				miConexion = instancia.getConexion();
				oStmt = miConexion.createStatement();
				
				ResultSet espremium = oStmt.executeQuery("SELECT TIPO_CUENTA FROM CUENTA WHERE LOGIN='"+login+"'");
				espremium.next();
				
				int premium =espremium.getInt("TIPO_CUENTA");
				if(premium==2){
					request.setAttribute("CuentaPremium", "Tu cuenta ya es premium");
					request.getRequestDispatcher("/WEB-INF/tiendaCliente.jsp").forward(request, response);
				}
				else{
					sSQL = "UPDATE Cuenta " +
							"SET puntos = puntos - 100, Tipo_Cuenta = 2 "+
							"WHERE LOGIN='"+login+'"';
					oStmt.executeUpdate(sSQL);
					
					request.getRequestDispatcher("/WEB-INF/homeCliente.jsp").forward(request, response);
				}		
			} catch (SQLException e1) {
				e1.printStackTrace();
			}		
			
			
		}
	}

}
