package com.casino.modelo.logica.usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Registro
 */

@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
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
		
		DataSource miDS = null;
		//pedimos el contexto de nuestro servidor
		Context ic = null;
		Connection conexion=null;
		Statement oStmt = null;
		String sSQL = null;
		
		try {
			ic = new InitialContext();
			miDS = (DataSource) ic.lookup("java:comp/env/jdbc/DataSourceLocal1");
			conexion=miDS.getConnection();
			oStmt = conexion.createStatement();
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String login = request.getParameter("usuario");
		String password = request.getParameter("password");
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		String email = request.getParameter("email");
		
		sSQL = "INSERT INTO Usuario " +
				"(login, pass, tipo_user) " +
				"VALUES ('"+login+"', '"+password+"', 1)"; //Tipo usuario 1, es el cliente normal
		
		try {
			oStmt.executeUpdate(sSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(fecha_nacimiento);
		sSQL = "INSERT INTO Cliente " +
				"(login, nombre, apellido, mail, fecha_nac) " +
				"VALUES ('"+login+"', '"+nombre+"', '"+apellido+"', '"+email+"', to_date('"+fecha_nacimiento+"', 'yyyy/mm/dd'))";
		
		try {
			oStmt.executeUpdate(sSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/pagina").forward(request, response);
		
	}

}
