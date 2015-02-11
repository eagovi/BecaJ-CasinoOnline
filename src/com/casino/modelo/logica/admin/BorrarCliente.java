package com.casino.modelo.logica.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casino.dataService.DameConexion;

/**
 * Servlet implementation class BorrarCliente
 */
@WebServlet("/BorrarCliente")
public class BorrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DameConexion instancia = DameConexion.getInstancia();
		String login = (String) request.getParameter("usuarioBorrar");
		Statement oStmt = null;
		Connection miConexion=null;
		try {
			miConexion= instancia.getConexion();
			oStmt =miConexion.createStatement();
			oStmt.executeUpdate("UPDATE usuario SET borrar=1 WHERE login='"+login+"'");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	request.getRequestDispatcher("/WEB-INF/clientesAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
