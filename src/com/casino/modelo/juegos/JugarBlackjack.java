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
	private int cantidadApostada;
	private Random generador;
       
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
    	generador = new Random();
    	cuenta = 0;
    	cuentaCasino = 0;
    	cantidadApostada = 0;
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
			
				//Accion pedir, todas lasa variables inicializadas se da carta y se controla si se ha pasado de 21
				if(accion.equals("Pedir")) {
					
					//Generamos una carta aleatoria
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
					if(cuenta > 21) {
						request.setAttribute("final", "si");
						int id_balance = ConsultasJuego.getInstancia().obtenerBalance(login);
						ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, -cantidadApostada);
					} else {
						request.setAttribute("final", "no");
					}
					
					request.setAttribute("plantarse", "no");
					request.setAttribute("inicio", "no");
					request.setAttribute("jugadaCasinoTerminada", "no");
					request.setAttribute("apuesta", "si");
					request.setAttribute("cantidadApostada", cantidadApostada);
					
				}	
				else if(accion.equals("Reiniciar")) {
					
					listaCartasCliente = new ArrayList<String>();
					listaCartasCasino = new ArrayList<String>();
			    	cuenta = 0;
			    	cuentaCasino = 0;
			    	cantidadApostada = 0;
			    	request.setAttribute("inicio", "si");
			    	request.setAttribute("final", "no");
			    	request.setAttribute("plantarse", "no");
			    	request.setAttribute("jugadaCasinoTerminada", "no");
			    	request.setAttribute("apuesta", "no");
			    	request.setAttribute("cantidadApostada", cantidadApostada);
			    	
				} else if(accion.equals("Plantarse")) {
					
					request.setAttribute("plantarse", "si");
					request.setAttribute("inicio", "no");
					request.setAttribute("final", "no");
					request.setAttribute("listaCartasCliente", listaCartasCliente);
					request.setAttribute("cuenta", cuenta);
					request.setAttribute("jugadaCasinoTerminada", "no");
					request.setAttribute("apuesta", "si");
					request.setAttribute("cantidadApostada", cantidadApostada);
					
				} else if(accion.equals("Resolver")) {
					
					request.setAttribute("plantarse", "si");
					request.setAttribute("inicio", "no");
					request.setAttribute("final", "no");
					request.setAttribute("listaCartasCliente", listaCartasCliente);
					request.setAttribute("cuenta", cuenta);
					request.setAttribute("apuesta", "si");
					request.setAttribute("cantidadApostada", cantidadApostada);

					int cartaCasino = generador.nextInt(13);
					int paloCasino = generador.nextInt(4);
					Baraja baraja = Baraja.getInstancia();
					String rutaCartaCasino = baraja.getMazo(paloCasino).get(cartaCasino);
					listaCartasCasino.add(rutaCartaCasino);
					request.setAttribute("listaCartasCasino", listaCartasCasino);
					cuentaCasino = cuentaCasino + baraja.valor(cartaCasino);
					request.setAttribute("cuentaCasino", cuentaCasino);
					
					//Cuando el casino se pase o se plante termina la jugada
					if((cuentaCasino >= cuenta) || (cuentaCasino > 21)) {
						request.setAttribute("jugadaCasinoTerminada", "si");
						
						int id_balance = ConsultasJuego.getInstancia().obtenerBalance(login);
						
						//Aqui se actualizan la base de datos de acuerdo con los resultados
						if(cuentaCasino > 21 && cuenta < 21) {
							//ganas
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, cantidadApostada);
							request.setAttribute("mensajeFin", "Has ganado "+cantidadApostada+" puntos");
						}
						else if(cuentaCasino > 21 && cuenta > 21) {
							//empate
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, 0);
							request.setAttribute("mensajeFin", "Empate, no ganas nada");
						}
						else if(cuenta > cuentaCasino) {
							//ganas
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, cantidadApostada);
							request.setAttribute("mensajeFin", "Has ganado "+cantidadApostada+" puntos");
						} else {
							//pierdes
							ConsultasJuego.getInstancia().actualizarPuntosCuenta(id_balance, login, -cantidadApostada);
							request.setAttribute("mensajeFin", "Has perdido "+cantidadApostada+" puntos");
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
					cantidadApostada = Integer.parseInt( (String) request.getParameter("apuesta"));
					
					
					int puntosActuales = ConsultasJuego.getInstancia().damePuntosCliente(login);
					//Comprobar si no tienes puntos
					if(puntosActuales >= cantidadApostada) {
						request.setAttribute("cantidadApostada", cantidadApostada);
						request.setAttribute("apuesta", "si");
					} else {
						request.setAttribute("apuesta", "no");
						request.setAttribute("mensajeSinPuntos", "No tienes suficientes puntos, compra mas :)");
					}
				}
					
			request.getRequestDispatcher("/WEB-INF/juegos/Blackjack.jsp").forward(request, response);
			
		}
	}
}
