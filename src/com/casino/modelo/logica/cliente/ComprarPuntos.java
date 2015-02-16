package com.casino.modelo.logica.cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.casino.dao.CompraVentaPuntos;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class ComprarPuntos
 */
@WebServlet("/ComprarPuntos")
public class ComprarPuntos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://172.22.7.10:8080/FriedBaconBank/rest/servicio/payday";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarPuntos() {
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
	
		HttpSession session = request.getSession(false);
		if(session != null) {
			
			String login = (String) session.getAttribute("nombre");
	
			//WebService
			ClientConfig 	config  = new DefaultClientConfig();
			Client			client  = Client.create(config);
			WebResource		service	= client.resource(REST_URI);
			String respuesta;
			
			String usuario = request.getParameter("usuario");
			String clave = request.getParameter("pass");
			String cantidad = request.getParameter("puntos");
			
			respuesta = service.queryParam("user", usuario)
							   .queryParam("pass", clave)
							   .queryParam("amount", cantidad)
							   .accept(MediaType.TEXT_PLAIN)
							   .get(String.class);	
			
			if(respuesta.equals("Pago correcto")) {
				//Modificaciones sobre la base de datos
				int puntosComprados = Integer.parseInt(request.getParameter("puntos"));
				
				int id_transaccion = CompraVentaPuntos.getInstancia().dameIdTransaccion(login);
				
				CompraVentaPuntos.getInstancia().sumarPuntos(login, id_transaccion, puntosComprados);
				
				request.getRequestDispatcher("/WEB-INF/homeCliente.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/paginas/indexError.html").forward(request, response);
			}
		}
	}

}
