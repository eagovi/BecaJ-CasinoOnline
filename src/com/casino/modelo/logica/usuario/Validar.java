package com.casino.modelo.logica.usuario;

import java.io.IOException;
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
 * Servlet implementation class Validar
 */
@WebServlet("/Validar")
public class Validar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validar() {
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
		
		DameConexion instancia = DameConexion.getInstancia();
		
		String login = request.getParameter("usuario");
		String pass = request.getParameter("password");
		
		ResultSet rs = null;

		try {
			Statement oStmt = instancia.getConexion().createStatement();
			rs = oStmt.executeQuery("SELECT * FROM Usuario WHERE login='"+login+"' AND pass="+pass);
			HttpSession session = request.getSession();
			session.setAttribute("nombre", request.getParameter("usuario"));
			
			if(rs.next()) {
				int tipo = Integer.valueOf(rs.getString("tipo_user"));
				//User normal
				if(tipo == 1) {
					request.getRequestDispatcher("/paginas/homeCliente.jsp").forward(request, response);
				}
				//User admin
				else {
					request.getRequestDispatcher("/paginas/homeAdmin.jsp").forward(request, response);
				}
			}
			else {
				response.sendRedirect("paginas/indexError.html");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
