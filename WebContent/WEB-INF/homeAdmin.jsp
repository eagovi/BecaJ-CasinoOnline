<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.casino.dataService.DameConexion"%>
<!DOCTYPE html>
<html>
	</head>
		<meta charset="ISO-8859-1">
		<title>Home</title>
		<link rel="StyleSheet" type="text/css" href="estilos/estiloHome.css"/>
	</head>
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="home.html"><img class="logo" src="../imagenes/logo.png" alt="logo"/></a>
					<p>Bienvenido</p>
			</div><!-- FIN CABECERA-->
			<div class="menu">
			<ul class="listaMenuAdmin">
				<a class="enlace" href="#"><li>Home</li></a>
				<a class="enlace" href="#"><li>Clientes</li></a>
				<a class="enlace" href="#"><li>Noticias</li></a>
				<a class="enlace" href="#"><li>Log out</li></a>
			</ul>
			</div>
			<div>
					<table>
						<tr>
							<th>Clientes</th>
						</tr>
						<tr>
							<td>Imagen</td>
							<td>Nombre</td>
							<td>Apellido</td>
							<td>Fecha de nacimiento</td>
							<td>Mail</td>
							<td>Puntos</td>
							<td>Tipo de cuenta</td>
						</tr>
						<%
						DameConexion instancia = DameConexion.getInstancia();
						Connection conexion = instancia.getConexion();
						
						Statement oStmt = conexion.createStatement();
						Statement oStmtPuntos = conexion.createStatement();
						
						ResultSet rs = oStmt.executeQuery("SELECT login, nombre, apellido, fecha_nac, mail, imagen FROM Cliente");
						ResultSet rsPuntos = null;
						
						while(rs.next()) {
							String login = rs.getString("login");
							rsPuntos = oStmtPuntos.executeQuery("SELECT puntos, tipo_cuenta FROM Cuenta WHERE login='"+login+"'");
							rsPuntos.next();%>
							<tr>
								<td><%=rs.getString("imagen")%></td>
								<td><%=rs.getString("nombre")%></td>
								<td><%=rs.getString("apellido")%></td>
								<td><%=rs.getString("fecha_nac")%></td>
								<td><%=rs.getString("mail")%></td>
								<td><%=rsPuntos.getString("puntos")%></td>
								<td><%=rsPuntos.getString("tipo_cuenta")%></td>
							</tr>
						<%} %>	
				</table>
			</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>