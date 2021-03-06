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
 * Servlet implementation class VenderPuntos
 */
@WebServlet("/VenderPuntos")
public class VenderPuntos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://172.22.7.6:8080/FriedBaconBank/rest/servicio/ingreso";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenderPuntos() {
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
			int puntos = Integer.parseInt(request.getParameter("puntos"));
			int cantidad = 0;
			
			switch (puntos) {
			case 100:
				cantidad = 10;
			break;
			case 300:
				cantidad = 30;
			break;
			case 500:
				cantidad = 45;
			break;
			case 1000:
				cantidad = 90;
			break;
			default:
				break;
			}
			
			respuesta = service.queryParam("user", usuario)
							   .queryParam("pass", clave)
							   .queryParam("amount", cantidad+"")
							   .accept(MediaType.TEXT_PLAIN)
							   .get(String.class);	
			
			if(respuesta.equals("Ingreso correcto")) {
				//Modificaciones sobre la base de datos
				int puntosComprados = Integer.parseInt(request.getParameter("puntos"));
				
				int id_transaccion = CompraVentaPuntos.getInstancia().dameIdTransaccion(login);
				
				CompraVentaPuntos.getInstancia().restarPuntos(login, id_transaccion, puntosComprados);
				
				request.getRequestDispatcher("/WEB-INF/homeCliente.jsp").forward(request, response);
			} else {
				System.out.println(respuesta);
			}
		}
	}

}
