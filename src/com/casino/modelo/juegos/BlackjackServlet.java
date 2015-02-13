package com.casino.modelo.juegos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Blackjack
 */
@WebServlet("/BlackjackServlet")
public class BlackjackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackjackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Preparacion de recursos del juego
		request.setAttribute("inicio", "si");
		request.setAttribute("final", "no");
		request.setAttribute("plantarse", "no");
		request.setAttribute("jugadaCasinoTerminada", "no");
		request.setAttribute("listaCartaCliente", null);
		request.setAttribute("listaCartaCasino", null);
		request.setAttribute("cuenta", 0);
		request.setAttribute("cuentaCasino", 0);
		request.setAttribute("apuesta", "no");
		request.getRequestDispatcher("/WEB-INF/juegos/Blackjack.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
