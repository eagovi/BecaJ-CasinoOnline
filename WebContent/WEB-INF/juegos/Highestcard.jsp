<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.casino.dataService.DameConexion"%>
<%@page import="com.casino.modelo.cartas.Baraja"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>HighestCard</title>
	<link rel="StyleSheet" type="text/css" href="estilos/estiloJuego.css"/>
</head>
<body>
	<div class="contenedor">
		<div class="cabecera">
				<a href="home.html"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
				<p>HighestCard</p>
		</div>
		<div class="mesaJuego">
			<img class="baraja" alt="mazo" src="imagenes/juegos/mazo1.png">
			<img class="mano" alt="mano" src="imagenes/juegos/mano.png">
			<table class="cartas">
				<tr>
					<th>Nuestra carta</th>
					<th>Tu carta</th>
				</tr>
				<tr>
					<%
					String jugado = (String) request.getAttribute("jugado");
					if(jugado.equals("no")) {
					%>
						<td><img alt="carta_casino" src="imagenes/juegos/mazo1.png"></td>
						<td><img alt="tu_carta" src="imagenes/juegos/mazo1.png"></td>
					<%} else if(jugado.equals("si")) {
						int cartaCasino = (Integer) request.getAttribute("cartaCasino");
						int cartaCliente = (Integer) request.getAttribute("cartaCliente");
						int paloCasino = (Integer) request.getAttribute("paloCasino");
						int paloCliente = (Integer) request.getAttribute("paloCliente");
						Baraja baraja = Baraja.getInstancia();
						String rutaCartaCasino = baraja.getMazo(paloCasino).get(cartaCasino);
						String rutaCartaCliente = baraja.getMazo(paloCliente).get(cartaCliente);
					%>
						<td><img alt="carta_casino" src="<%=rutaCartaCasino%>"></td>
						<td><img alt="tu_casino" src="<%=rutaCartaCliente%>"></td>
					<%}%>
				</tr>
			</table>
			
			<table class="botones">
				<tr>
					<td>
						<form action="JugarHighestcard" method="post">
							<select name="apuesta"> 
								<option selected value = 100> 100 puntos
								<option  value=200> 200 puntos
								<option value=500> 500 puntos
							</select> 
							<input type="submit" value="Jugar">
						</form>
					</td>
				</tr>
			</table>
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
		</div>
	</div>
</body>
</html>