package com.casino.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontControllerCliente")
public class FrontControllerCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControllerCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		switch (accion) {
		case "homeCliente":
			request.getRequestDispatcher("/WEB-INF/homeCliente.jsp").forward(request, response);
		break;
		case "jugarCliente": 
			request.getRequestDispatcher("/WEB-INF/jugarCliente.jsp").forward(request, response);
		break;
		case "tiendaCliente":
			request.getRequestDispatcher("/WEB-INF/tiendaCliente.jsp").forward(request, response);
		break;
		case "promocionesCliente":
			request.getRequestDispatcher("/WEB-INF/promocionesCliente.jsp").forward(request, response);
		break;
		case "atencionCliente":
			request.getRequestDispatcher("/WEB-INF/atencionCliente.jsp").forward(request, response);
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
