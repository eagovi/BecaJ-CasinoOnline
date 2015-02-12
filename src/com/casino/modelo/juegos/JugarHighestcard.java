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
 * Servlet implementation class JugarHighestcard
 */
@WebServlet("/JugarHighestcard")
public class JugarHighestcard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JugarHighestcard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Hola");
		HttpSession  session = request.getSession();
		if(!session.isNew()) {
			
			int apuesta = Integer.parseInt(request.getParameter("apuesta"));
			session = request.getSession();
			String login = (String) session.getAttribute("nombre");
			
			DameConexion instancia = DameConexion.getInstancia();
			Connection conexion = instancia.getConexion();
			
			try {
				Statement oStmt = conexion.createStatement();
			
				ResultSet rs = oStmt.executeQuery("SELECT id_balance "+ 
												"FROM Caja WHERE login='"+login+"'");
				rs.next();
				
				String id_balance = rs.getString("id_balance");
				
				/*ResultSet rsPuntos = oStmt.executeQuery("SELECT puntos "+ 
						"FROM Cuenta WHERE login='"+login+"'");
				rsPuntos.next();*/
				
				/*int puntos = Integer.parseInt(rsPuntos.getString("puntos"));
				if(puntos < 0) {*/
				
					Random generador = new Random();
					
					int cartaCliente = generador.nextInt(12);
					int cartaCasino = generador.nextInt(12);
					int paloCliente = generador.nextInt(4);
					int paloCasino = generador.nextInt(4);
					
					request.setAttribute("jugado", "si");
					request.setAttribute("cartaCasino", cartaCasino);
					request.setAttribute("cartaCliente", cartaCliente);
					request.setAttribute("paloCliente", paloCliente);
					request.setAttribute("paloCasino", paloCasino);
					
					
					if(cartaCliente > cartaCasino) {
						request.setAttribute("mensajeFinalCartaAlta", "Has ganado!! :(");
						oStmt.executeUpdate("INSERT INTO balance(id_balance, puntos, fecha, id_juego) "
								+"values("+id_balance+", "+(apuesta)+", sysdate, 3)");
						
						oStmt.executeUpdate("UPDATE cuenta "+
												"SET puntos = puntos +"+apuesta+
												"WHERE login = '"+login+"'");
					}
					else  {
						request.setAttribute("mensajeFinalCartaAlta", "Has perdido!! :)");
						oStmt.executeUpdate("INSERT INTO balance(id_balance, puntos, fecha, id_juego) "
								+"values("+id_balance+", "+(apuesta*(-1))+", sysdate, 3)");
						
						oStmt.executeUpdate("UPDATE cuenta "+
								"SET puntos = puntos -"+apuesta+
								"WHERE login = '"+login+"'");
					}
				/*}
				else {
					request.setAttribute("mensajeFinalCartaAlta", "No tienes puntos perderdor");
				}*/
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			request.getRequestDispatcher("/WEB-INF/juegos/Highestcard.jsp").forward(request, response);
		}
	}

}
