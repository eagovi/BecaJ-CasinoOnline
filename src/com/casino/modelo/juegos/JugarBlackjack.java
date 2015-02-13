package com.casino.modelo.juegos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.casino.dao.ConsultasJuego;
import com.casino.modelo.cartas.Baraja;

/**
 * Servlet implementation class JugarBlackJack
 */
@WebServlet("/JugarBlackjack")
public class JugarBlackjack extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> listaCartasCliente;
	private ArrayList<String> listaCartasCasino;
	private int cuenta;
	private int cuentaCasino;
	private int apuesta;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JugarBlackjack() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
    	listaCartasCliente = new ArrayList<String>();
    	listaCartasCasino = new ArrayList<String>();
    	cuenta = 0;
    	cuentaCasino = 0;
    	apuesta = 0;
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
			
			String login = (String) session.getAttribute("nombre");	
			
			//Comprobamos que boton se ha pulsado
			String accion = request.getParameter("accion");
			System.out.println(accion);
			//Accion pedir, todas lasa variables inicializadas se da carta y se controla si se ha pasado de 21
			
				if(accion.equals("Pedir")) {
					
					//Generamos una carta aleatoria
					Random generador = new Random();
					int cartaCliente = generador.nextInt(13);
					int paloCliente = generador.nextInt(4);
					
					//Añadimos la carta al arraylist
					Baraja baraja = Baraja.getInstancia();
					String rutaCartaCliente = baraja.getMazo(paloCliente).get(cartaCliente);
					listaCartasCliente.add(rutaCartaCliente);
					request.setAttribute("listaCartasCliente", listaCartasCliente);
					
					//Sumamos el valor de la carta a la cuenta
					cuenta = cuenta + baraja.valor(cartaCliente);
					request.setAttribute("cuenta", cuenta);
					System.out.println(cuenta);
					if(cuenta > 21) {
						request.setAttribute("final", "si");
					} else {
						request.setAttribute("final", "no");
					}
					
					request.setAttribute("plantarse", "no");
					request.setAttribute("inicio", "no");
					request.setAttribute("jugadaCasinoTerminada", "no");
					request.setAttribute("apuesta", "si");
					
				}	
				else if(accion.equals("Reiniciar")) {
					
					listaCartasCliente = new ArrayList<String>();
					listaCartasCasino = new ArrayList<String>();
			    	cuenta = 0;
			    	cuentaCasino = 0;
			    	apuesta = 0;
			    	request.setAttribute("inicio", "si");
			    	request.setAttribute("final", "no");
			    	request.setAttribute("plantarse", "no");
			    	request.setAttribute("jugadaCasinoTerminada", "no");
			    	request.setAttribute("apuesta", "no");
			    	
				} else if(accion.equals("Plantarse")) {
					
					request.setAttribute("plantarse", "si");
					request.setAttribute("inicio", "no");
					request.setAttribute("final", "no");
					request.setAttribute("listaCartasCliente", listaCartasCliente);
					request.setAttribute("cuenta", cuenta);
					request.setAttribute("jugadaCasinoTerminada", "no");
					request.setAttribute("apuesta", "si");
					
				} else if(accion.equals("Resolver")) {
					
					request.setAttribute("plantarse", "si");
					request.setAttribute("inicio", "no");
					request.setAttribute("final", "no");
					request.setAttribute("listaCartasCliente", listaCartasCliente);
					request.setAttribute("cuenta", cuenta);
					request.setAttribute("apuesta", "si");
					
					Random generador = new Random();
					int cartaCasino = generador.nextInt(13);
					int paloCasino = generador.nextInt(4);
					Baraja baraja = Baraja.getInstancia();
					String rutaCartaCasino = baraja.getMazo(paloCasino).get(cartaCasino);
					listaCartasCasino.add(rutaCartaCasino);
					request.setAttribute("listaCartasCasino", listaCartasCasino);
					cuentaCasino = cuentaCasino + baraja.valor(cartaCasino);
					request.setAttribute("cuentaCasino", cuentaCasino);
					
					//Cuando el casino se pase o se plante termina la jugada
					if(cuentaCasino >= 17) {
						request.setAttribute("jugadaCasinoTerminada", "si");
						
						int id_balance = ConsultasJuego.getInstancia().obtenerBalance(login);
						
						//Aqui se actualizan la base de datos de acuerdo con los resultados
						if(cuentaCasino > 21 && cuenta < 21) {
							//ganas
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, apuesta);
						}
						else if(cuentaCasino > 21 && cuenta > 21) {
							//empate
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, 0);
						}
						else if(cuenta > cuentaCasino) {
							//ganas
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, apuesta);
						} else {
							//pierdes
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, -apuesta);
						}
					}
					else {
						request.setAttribute("jugadaCasinoTerminada", "no");
					}
				} else if(accion.equals("Apostar")) {
					request.setAttribute("inicio", "si");
					request.setAttribute("final", "no");
					request.setAttribute("plantarse", "no");
					request.setAttribute("jugadaCasinoTerminada", "no");
					request.setAttribute("listaCartaCliente", new ArrayList<String>());
					request.setAttribute("listaCartaCasino", new ArrayList<String>());
					request.setAttribute("cuenta", 0);
					request.setAttribute("cuentaCasino", 0);
					request.setAttribute("apuesta", "si");
					apuesta = Integer.parseInt( (String) request.getParameter("apuesta"));
				}
					
			request.getRequestDispatcher("/WEB-INF/juegos/Blackjack.jsp").forward(request, response);
			
		}
	}
}
