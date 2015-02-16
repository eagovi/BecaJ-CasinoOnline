package com.casino.modelo.juegos;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.casino.dao.ConsultasJuego;

/**
 * Servlet implementation class JugarHighestcard
 */
@WebServlet("/JugarHighestcard")
public class JugarHighestcard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Random generador;
	private int cartaCliente;
	private int cartaCasino;
	private int paloCliente;
	private int paloCasino;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JugarHighestcard() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
    	generador = new Random();
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

		HttpSession  session = request.getSession();
		if(!session.isNew()) {
			
			int apuesta = Integer.parseInt(request.getParameter("apuesta"));
			session = request.getSession();
			String login = (String) session.getAttribute("nombre");	
				
			int puntosActuales = ConsultasJuego.getInstancia().damePuntosCliente(login);
			if(puntosActuales > apuesta) {
			
					cartaCliente = generador.nextInt(12);
					cartaCasino = generador.nextInt(12);
					paloCliente = generador.nextInt(4);
					paloCasino = generador.nextInt(4);
					
					request.setAttribute("jugado", "si");
					request.setAttribute("cartaCasino", cartaCasino);
					request.setAttribute("cartaCliente", cartaCliente);
					request.setAttribute("paloCliente", paloCliente);
					request.setAttribute("paloCasino", paloCasino);
					
					
					int id_balance = ConsultasJuego.getInstancia().obtenerBalance(login);
					if(cartaCliente > cartaCasino) {
						request.setAttribute("mensajeFinalCartaAlta", "GANA EL CLIENTE :(");
						ConsultasJuego.getInstancia().actualizarPuntosCuentaHC(id_balance, login, apuesta);
					}
					else  {
						request.setAttribute("mensajeFinalCartaAlta", "GANA EL CASINO :)");
						ConsultasJuego.getInstancia().actualizarPuntosCuentaHC(id_balance, login, -apuesta);
					}
		
			} else {
				request.setAttribute("mensajeSinPuntos", "Te has quedad sin puntos, compra mas");
			}
			
			request.getRequestDispatcher("/WEB-INF/juegos/Highestcard.jsp").forward(request, response);
		}
	}


}
