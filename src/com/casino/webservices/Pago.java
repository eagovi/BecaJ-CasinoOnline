package com.casino.webservices;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Pago
 */
@WebServlet("/Pago")
public class Pago extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static final String REST_URI = "http://localhost:8080/FriedBaconBank/rest/servicio/payday";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pago() {
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
		
		ClientConfig 	config  = new DefaultClientConfig();
		Client			client  = Client.create(config);
		WebResource		service	= client.resource(REST_URI);
		String respuesta;
		
		respuesta = service.queryParam("user", request.getParameter("user"))
						   .queryParam("pass", request.getParameter("pass"))
						   .queryParam("amount", request.getParameter("amount"))
						   .accept(MediaType.TEXT_PLAIN)
						   .get(String.class);
		
		
		System.out.println(respuesta);


	
	}

}
