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
		<link rel="StyleSheet" type="text/css" href="estilos/pruebas.css"/>
	</head>
	<body>
		<div class="contenedor"><!-- Inicio del contenedor-->
			<div class="cabecera">
				<a href="home.html"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
					<p>Bienvenido</p>
			</div><!-- FIN CABECERA-->
			<div class="menu">
			<ul class="listaMenu">
				<a class="enlace" href="#"><li>Home</li></a>
				<a class="enlace" href="FrontControllerCliente?accion=jugarCliente"><li>Jugar</li></a>
				<a class="enlace" href="FrontControllerCliente?accion=tiendaCliente"><li>Tienda</li></a>
				<a class="enlace" href="FrontControllerCliente?accion=promocionesCliente"><li>Promociones</li></a>
				<a class="enlace" href="Salir"><li>Log out</li></a>
			</ul>
			</div>
				<div id="gifcliente">
					<img class="gifcliente" src="imagenes/ImgCliente.gif" alt="GIF"/>
				</div>
				
				<div class="informacionCliente">
						
					<%
						DameConexion instancia = DameConexion.getInstancia();
						Connection conexion = instancia.getConexion();
						
						Statement oStmt = conexion.createStatement();
						Statement oStmtPuntos = conexion.createStatement();
						Statement oStmtTipo = conexion.createStatement();
						session = request.getSession();
						String login = (String) session.getAttribute("nombre");
					
						ResultSet rs = oStmt.executeQuery("SELECT login, nombre, apellido, fecha_nac, mail, imagen "+ 
																	"FROM Cliente WHERE login='"+login+"'");
						ResultSet rsPuntos = oStmtPuntos.executeQuery("SELECT puntos, tipo_cuenta "+
																	"FROM Cuenta WHERE login='"+login+"'");
						
						rs.next();
						rsPuntos.next();
						
						ResultSet rsTipo = oStmtTipo.executeQuery("SELECT nombre "+
																	"FROM Tipo WHERE tipo_cuenta='"+rsPuntos.getString("tipo_cuenta")+"'");
						rsTipo.next();										
						%>
						<table class="tablaDatosCliente">
							<tr>
								<td rowspan="2"><img src="<%=rs.getString("imagen")%>"></td>
								<td><%=rs.getString("nombre")%></td>
							</tr>
							<tr>
								<td><%=rs.getString("apellido")%></td>
							</tr>
							
						</table>
						
						<table class="tablaDatos">	
							<tr>
								<td>Puntos: </td>
								<td><%=rsPuntos.getString("puntos")%></td>
								<td>Tipo de cuenta: </td>
								<td><%=rsTipo.getString("nombre")%></td>
							</tr>
						</table>
						
						<%
						
						ResultSet rsnoticias = oStmt.executeQuery("SELECT id_noticias, titular, imagen, contenido, fecha FROM Noticias order by fecha desc");					
						rsnoticias.next();									
						%>
						
						<table class="tablaNoticias">
							<tr>
								<td rowspan="2"><img src="<%=rsnoticias.getString("imagen")%>"></td>
							</tr>
							<tr>
								<td><%=rsnoticias.getString("fecha")%></td>
							</tr>
							<tr>
								<td><%=rsnoticias.getString("titular")%></td>
							</tr>
							<tr>
								<td><%=rsnoticias.getString("contenido")%></td>
							</tr>
							
						</table>
				</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>