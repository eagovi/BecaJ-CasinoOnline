<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.casino.dataService.DameConexion"%>
<%@page import="com.casino.modelo.cartas.Baraja"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Blackjack</title>
	<link rel="StyleSheet" type="text/css" href="estilos/estiloJuegoBlackjack.css"/>
</head>
<body>
	<div class="contenedor">
		<div class="cabecera">
				<a href="home.html"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
				<p>Blackjack</p>
		</div>
		<div class="mesaJuego">
			<%if(request.getAttribute("apuesta").equals("si")) {%>
				<% if(request.getAttribute("jugadaCasinoTerminada").equals("no")) {%>
					<table class="cartasCrupier">
						<% if(request.getAttribute("plantarse").equals("no")) {%>
						<tr>
							<td><img alt="carta" src="imagenes/juegos/mazo1.png"></td>
							
						<%} else {
		
							if(request.getAttribute("jugadaCasinoTerminada").equals("no")) {
								request.getRequestDispatcher("/JugarBlackjack?accion=Resolver").forward(request, response);	
							}%>
							
							<%ArrayList<String> listaCasino = (ArrayList<String>) request.getAttribute("listaCartasCasino");%>
									<th>Llevamos: <%=request.getAttribute("cuentaCasino")%></th>
								<%for(int i = 0; i < listaCasino.size(); i++) {%>
									<td><img  class="cartaTransicion" alt="tu_carta" src="<%=listaCasino.get(i)%>"></td>
							<%}%>
						</tr>
						<% }%>
					</table>
					
					<table class="cartasCliente">
						<tr>
							<%
							if(request.getAttribute("inicio").equals("si")){
							%>
								<td><img alt="carta" src="imagenes/juegos/mazo1.png"></td>
							<% }
							else {
									ArrayList<String> lista = (ArrayList<String>) request.getAttribute("listaCartasCliente");
										%><th>Llevas: <%=request.getAttribute("cuenta")%></th>
									<%for(int i = 0; i < lista.size(); i++) {%>
										<td><img alt="tu_carta" src="<%=lista.get(i)%>"></td>
									<%}%>
							<%} %>
						</tr>
						
					</table>
					<form action="JugarBlackjack" method="post">
					<table class="botones">
						<% if(request.getAttribute("plantarse").equals("no")) {%>
							<% if(request.getAttribute("final").equals("no")) {%>
								<tr>
									<td>
										<input type="submit" name="accion" value="Pedir">
									</td>
									<td>
										<input type="submit" name="accion" value="Plantarse">
									</td>
								</tr>
								
							<%} else { %>
								<tr>
									<td>Te has pasado</td>
									<td><input type="submit" name="accion" value="Reiniciar"></td>
								</tr>
							<%} %>
						<%} %>
					</table>
					</form>
					
					<%
						DameConexion instancia = DameConexion.getInstancia();
						Connection conexion = instancia.getConexion();
						
						Statement oStmt = conexion.createStatement();
						Statement oStmtPuntos = conexion.createStatement();
						Statement oStmtTipo = conexion.createStatement();
						session = request.getSession();
						String login = (String) session.getAttribute("nombre");
					
						ResultSet rs = oStmt.executeQuery("SELECT nombre, imagen "+ 
																	"FROM Cliente WHERE login='"+login+"'");
						ResultSet rsPuntos = oStmtPuntos.executeQuery("SELECT puntos "+
																	"FROM Cuenta WHERE login='"+login+"'");
						rs.next();
						rsPuntos.next();
					%>
					<table class="infoCliente">
						<tr>
							<td rowspan="2"><img src="<%=rs.getString("imagen")%>"></td>
							<td><%=rs.getString("nombre")%></td>
						</tr>
						<tr>
							<td><%=rsPuntos.getString("puntos")%> puntos</td>
						</tr>	
					</table>
					
				<%} else {%>
				
						<table class="cartasCrupier">
							<tr>
								<%ArrayList<String> listaCasino = (ArrayList<String>) request.getAttribute("listaCartasCasino");%>
								
								<th>Llevamos: <%=request.getAttribute("cuentaCasino")%></th>
								<%for(int i = 0; i < listaCasino.size(); i++) {%>
									<td><img alt="tu_carta" src="<%=listaCasino.get(i)%>"></td>
								<%}%>
							</tr>
						</table>
		
						<form action="JugarBlackjack" method="post">
							<table class="mensajeFinal">
							
								<tr>
									<%
									String mensaje;
									if((Integer) request.getAttribute("cuentaCasino") > 21 && (Integer) request.getAttribute("cuenta") < 21) {
										mensaje = "Has ganado";
									}
									else if((Integer) request.getAttribute("cuentaCasino") > 21 && (Integer) request.getAttribute("cuenta") > 21) {
										mensaje = "Empate";
									}
									else if((Integer) request.getAttribute("cuenta") > (Integer) request.getAttribute("cuentaCasino") ) { 
										mensaje = "Has ganado!!";
									} else {
										mensaje = "Has perdido!!";
									}%>
									<td><%=mensaje%></td>
									<td><input type="submit" name="accion" value="Reiniciar"></td>
								</tr>
							</table>
						</form>	
						
						<table class="cartasCliente">
							<tr>
								<%
								ArrayList<String> lista = (ArrayList<String>) request.getAttribute("listaCartasCliente");
									%><th>Llevas: <%=request.getAttribute("cuenta")%></th>
								<%for(int i = 0; i < lista.size(); i++) {%>
									<td><img alt="tu_carta" src="<%=lista.get(i)%>"></td>
								<%}%>
							</tr>
							
						</table>
					<%} %>
				<%} else {%>
					<form action="JugarBlackjack" method="post">
						<table class="mensajeFinal">
							<tr>
								<td>
									<select name="apuesta"> 
										<option selected value = 100> 100 puntos
										<option  value=200> 200 puntos
										<option  value=200> 300 puntos
										<option  value=200> 400 puntos
										<option value=500> 500 puntos
									</select> 
								</td>
								<td>
									<input type="submit" name="accion" value="Apostar">
								</td>
							</tr>
						</table>
					</form>
				
				<%}%>
		</div>
		
		<div class="enlaceVolver">
			<a href="FrontControllerCliente?accion=jugarCliente"><h3>Volver</h3></a>
		</div>
	</div>
</body>
</html>