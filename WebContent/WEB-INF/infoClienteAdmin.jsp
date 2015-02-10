<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.casino.dataService.DameConexion"%>
<!DOCTYPE html>
<html>
	</head>
		<meta charset="ISO-8859-1">
		<title>Clientes</title>
		<link rel="StyleSheet" type="text/css" href="estilos/estiloHome.css"/>
	</head>
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="home.html"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
					<p>Bienvenido</p>
			</div><!-- FIN CABECERA-->
			<div class="menu">
			<ul class="listaMenuAdmin">
				<a class="enlace" href="FrontControllerAdmin?accion=homeAdmin"><li>Home</li></a>
				<a class="enlace_marcado" href="#"><li  class="marcado">Clientes</li></a>
				<a class="enlace" href="FrontControllerAdmin?accion=noticiasAdmin"><li>Noticias</li></a>
				<a class="enlace" href="Salir"><li>Log out</li></a>
			</ul>
			</div>
			<div class="informacionCliente">
				<table class="tablaDatosCliente">
					<%
						DameConexion instancia = DameConexion.getInstancia();
						Connection conexion = instancia.getConexion();
						
						
						String login = (String)request.getAttribute("loginCliente");
						Statement oStmt = conexion.createStatement();
						Statement oStmtPuntos = conexion.createStatement();
						
						ResultSet rsPuntos = null;
						ResultSet rs = oStmt.executeQuery("SELECT login, nombre, apellido, fecha_nac, mail, imagen FROM Cliente WHERE login = '"+login+"'");
						rsPuntos = oStmtPuntos.executeQuery("SELECT puntos, tipo_cuenta FROM Cuenta WHERE login='"+login+"'");
						rsPuntos.next();
						
						String tipoCuenta;
						int tipo = Integer.valueOf(rsPuntos.getString("tipo_cuenta"));
						if(tipo==2)
							tipoCuenta="Premium";
						else
							tipoCuenta="Normal";
						
						if(rs.next()) {
					%>
						<tr>
							<td colspan="2" class="cabezaTabla">DATOS CLIENTE</td>
						</tr>
						<tr>
							<td class="derechaTabla">Nombre usuario</td>
							<td><%=login%></td>
						</tr>
						<tr>
							<td class="derechaTabla">Imagen</td>
							<td><img class="imgUser" src="<%=rs.getString("imagen")%>"/></td>
						</tr>
						<tr>
							<td class="derechaTabla">Nombre</td>
							<td><%=rs.getString("nombre")%></td>
						</tr>
						<tr>
							<td class="derechaTabla">Apellido</td>
							<td><%=rs.getString("apellido")%></td>
						</tr>
						<tr>
							<td class="derechaTabla">Fecha de nacimiento</td>
							<td><%=rs.getString("fecha_nac")%></td>
						</tr>
						<tr>
							<td class="derechaTabla">Mail</td>
							<td><%=rs.getString("mail")%></td>
						</tr>
						<tr>
							<td class="derechaTabla">Puntos</td>
							<td><%=rsPuntos.getString("puntos")%></td>
						</tr>
						<tr>
							<td class="derechaTabla">Tipo de cuenta</td>
							<td><%=tipoCuenta%></td>
						</tr>
						<tr>
							<form action="Borrar" method="GET">
								<td class="boton_enviar"><input type="submit" name="borrar" value="Borrar usuario"/></td>
							</form>
							<form action="Premium" method="GET">
								<td><input type="submit" name="premium" value="Hacer premium"/></td>
							</form>
						</tr>
						<%}
							else {
								out.println("jose lleva bragas");
							}%>
				</table><!--FIN TABLA DATOS-->
				
				<table class="tablaPuntos">
					<tr>
						<td colspan="2" class="cabezaTabla">HISTORIAL DE PUNTOS</td>
					</tr>
				</table>
				
				<table class="tablaEstadisticas">
					<tr>
						<td colspan="2" class="cabezaTabla">ESTADISTICAS</td>
					</tr>
				</table>
				
				
			</div>
			<div class="pieCentrado">
				<img class="pieImagen" src="imagenes/pie.png" alt="pie"/>
			</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>