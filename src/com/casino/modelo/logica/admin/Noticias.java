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
 * Servlet implementation class Noticias
 */
@WebServlet("/Noticias")
public class Noticias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Noticias() {
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
		
		String titular = request.getParameter("titularNoticia");
		String imagen = request.getParameter("imagenNoticia");
		String contenido = request.getParameter("contenidoNoticia");
		
		try {
			sSQL = "INSERT INTO Noticias (id_noticias, titular, imagen, contenido, fecha) VALUES (ID_NOTICIA.NEXTVAL, '"+titular+"', '"+imagen+"', '"+contenido+"', sysdate,'ddmmyyyy'))";
			System.out.println(sSQL);
			oStmt.executeUpdate(sSQL);
			System.out.println("NOTICIA ENVIADA, CABRONAZO.");
			
		} catch (SQLException e) {
			/*
			try {
				miConexion.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}*/
			e.printStackTrace();
			System.out.println("NO SE HA ENVIADO, RETRASADO");
		} 
	}

}
