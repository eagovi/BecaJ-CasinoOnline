<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.casino.dataService.DameConexion"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tienda</title>
		<link rel="StyleSheet" type="text/css" href="estilos/estiloCliente.css"/>
	</head>
	
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="homeCliente.jsp"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
					<p>Bienvenido</p>
			</div><!-- FIN CABECERA-->
			<div class="menu">
				<ul class="listaMenuCliente">
					<a class="enlace" href="FrontControllerCliente?accion=homeCliente"><li>Home</li></a>
					<a class="enlace_marcado" href="#"><li class="marcado">Jugar</li></a>
					<a class="enlace" href="FrontControllerCliente?accion=tiendaCliente"><li>Tienda</li></a>
					<a class="enlace" href="FrontControllerCliente?accion=promocionesCliente"><li>Promociones</li></a>
					<a class="enlace" href="Salir"><li>Log out</li></a>
				</ul>
			</div>

			<div class = "informacion">
				<table class = "tablaJuegos">
					<tr>
						<td colspan="3" class="cabezaTabla"><h2>Juegos</h2></td>
					</tr>
					<%
					
					DameConexion instancia = DameConexion.getInstancia();
					Connection conexion = instancia.getConexion();
					
					Statement oStmt = conexion.createStatement();
					Statement oStmtPuntos = conexion.createStatement();
					
					ResultSet rs = oStmt.executeQuery("SELECT nombre, descripcion, imagen FROM Juego");
					%><tr>
					<%while(rs.next()) {%>
						
							<td>
								<a title="<%=rs.getString("descripcion")%>" href="<%=rs.getString("nombre")%>Servlet"><%=rs.getString("nombre")%><br>
								<img src="<%=rs.getString("imagen")%>"></a>
								
							</td>
					<%}%>	
					</tr>				
				</table>
			</div>
			
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png">
			</div>
		
		</div><!--FIN CONTENEDOR-->
</body>
</html>