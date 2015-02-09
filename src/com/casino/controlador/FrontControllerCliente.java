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
		String accion = request.getParameter("Controlador");
		switch (accion) {
		case "Home":
			request.getRequestDispatcher("/").forward(request, response);
		break;
		case "Jugar": 
			request.getRequestDispatcher("/").forward(request, response);
		break;
		case "Promociones":
			request.getRequestDispatcher("/").forward(request, response);
		break;
		case "Cuenta":
			request.getRequestDispatcher("/").forward(request, response);
		break;
		case "Atencion":
			request.getRequestDispatcher("/").forward(request, response);
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
