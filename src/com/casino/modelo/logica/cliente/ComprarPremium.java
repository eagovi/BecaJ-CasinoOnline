package com.casino.modelo.logica.cliente;

import java.io.IOException;
import java.sql.Connection;
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
			String comprapremium = request.getParameter("premium");
			
			try {
				miConexion = instancia.getConexion();
				oStmt = miConexion.createStatement();
				
				sSQL = "UPDATE Cuenta " +
						"SET puntos = puntos - "+comprapremium +", Tipo_Cuenta = 2 "+
						"WHERE login = '"+login+"'";
				
				oStmt.executeUpdate(sSQL);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}		
			
			request.getRequestDispatcher("/WEB-INF/homeCliente.jsp").forward(request, response);
		}
	}

}
