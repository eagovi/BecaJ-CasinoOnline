package com.casino.modelo.logica.usuario;

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
		
		DameConexion instancia = DameConexion.getInstancia();
		
		Statement oStmt = null;
		String sSQL = null;
		Connection miConexion=null;
		try {
			miConexion= instancia.getConexion();
			oStmt =miConexion.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String login = request.getParameter("usuario");
		String password = request.getParameter("password");
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		String email = request.getParameter("email");
		String imagenDefecto = "http://b.thumbs.redditmedia.com/glxbKxKAbhICwzmg.png";
		try {
			sSQL = "INSERT INTO Usuario " +
					"(login, pass, tipo_user, borrar, fecha_creacion) " +
					"VALUES ('"+login+"', '"+password+"', 1, 0, sysdate)"; //Tipo usuario 1, es el cliente normal
			oStmt.executeUpdate(sSQL);
		
			sSQL = "INSERT INTO Cliente " +
					"(login, nombre, apellido, mail, fecha_nac, imagen) " +
					"VALUES ('"+login+"', '"+nombre+"', '"+apellido+"', '"+email+"', "
							+ "to_date('"+fecha_nacimiento+"', 'yyyy/mm/dd'), '"+imagenDefecto+"')";
			oStmt.executeUpdate(sSQL);
	
			sSQL = "INSERT INTO Cuenta " +
					"(login, puntos, tipo_cuenta) " +
					"VALUES ('"+login+"', "+500+", "+1+")";
			oStmt.executeUpdate(sSQL);
			
			sSQL = "INSERT INTO Caja " +
					"(login, id_banco, id_transaccion, id_balance) " +
					"VALUES ('"+login+"', 1, TRANSACC.nextval, ID_BALANCE.nextval)";
			oStmt.executeUpdate(sSQL);
			
			
			request.getRequestDispatcher("/paginas/indexExito.html").forward(request, response);
			
		} catch (SQLException e) {
			/*
			try {
				miConexion.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}*/
			request.getRequestDispatcher("/paginas/indexErrorRegistro.html").forward(request, response);
		} 	
	}

}
