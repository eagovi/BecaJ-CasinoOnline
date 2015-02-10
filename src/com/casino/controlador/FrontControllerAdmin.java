package com.casino.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControllerAdmin
 */
@WebServlet("/FrontControllerAdmin")
public class FrontControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch (accion) {
		case "homeAdmin":
			request.getRequestDispatcher("/WEB-INF/homeAdmin.jsp").forward(request, response);
		break;
		case "clientesAdmin": 
			request.getRequestDispatcher("/WEB-INF/clientesAdmin.jsp").forward(request, response);
		break;
		case "noticiasAdmin":
			request.getRequestDispatcher("/WEB-INF/noticiasAdmin.jsp").forward(request, response);
		break;
		case "infoClienteAdmin":
			String nombre = request.getParameter("nombre");
			request.setAttribute("loginCliente", nombre);
			request.getRequestDispatcher("/WEB-INF/infoClienteAdmin.jsp").forward(request, response);
		break;
		default:
			System.out.println("Accion no definida");
		break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
