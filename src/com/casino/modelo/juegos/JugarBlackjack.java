package com.casino.modelo.juegos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.casino.dataService.DameConexion;

/**
 * Servlet implementation class JugarBlackJack
 */
@WebServlet("/JugarBlackjack")
public class JugarBlackjack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JugarBlackjack() {
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
		HttpSession  session = request.getSession();
		if(!session.isNew()) {
			
			int apuesta = Integer.parseInt(request.getParameter("apuesta"));
			session = request.getSession();
			String login = (String) session.getAttribute("nombre");
			
			DameConexion instancia = DameConexion.getInstancia();
			Connection conexion = instancia.getConexion();
			
			String accion = request.getParameter("accion");
			
			try {
				if(accion.equals("Pedir")) {
					System.out.println("Pedir");
				}
				Statement oStmt = conexion.createStatement();
			
				ResultSet rs = oStmt.executeQuery("SELECT id_balance "+ 
												"FROM Caja WHERE login='"+login+"'");
				rs.next();
				
				String id_balance = rs.getString("id_balance");
				
				Random generador = new Random();
				
				int cartaCliente = generador.nextInt(12);
				int paloCliente = generador.nextInt(4);
				
				request.setAttribute("cartaCliente", cartaCliente);
				request.setAttribute("paloCliente", paloCliente);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			
			request.getRequestDispatcher("/WEB-INF/juegos/Blackjack.jsp").forward(request, response);
			
		}
	}
}
