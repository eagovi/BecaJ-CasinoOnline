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
			<div class="informacion">
				<table class="tablaDatos">
						<tr>
							<td class="tablaTop">Imagen</td>
							<td class="tablaTop">Nombre</td>
							<td class="tablaTop">Apellido</td>
							<td class="tablaTop">Fecha de nacimiento</td>
							<td class="tablaTop">Mail</td>
							<td class="tablaTop">Puntos</td>
							<td class="tablaTop">Tipo de cuenta</td>
						</tr>
						<%
						DameConexion instancia = DameConexion.getInstancia();
						Connection conexion = instancia.getConexion();
						
						Statement oStmt = conexion.createStatement();
						Statement oStmtPuntos = conexion.createStatement();
						
						ResultSet rs = oStmt.executeQuery("SELECT login, nombre, apellido, fecha_nac, mail, imagen FROM Cliente");
						ResultSet rsPuntos = null;
						
						while(rs.next()) {
							
							
							//String fecha=rs.getString("fecha_nac");
							//SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
							//Date fnacimiento = formato.parse(fecha);
							String login = rs.getString("login");
							rsPuntos = oStmtPuntos.executeQuery("SELECT puntos, tipo_cuenta FROM Cuenta WHERE login='"+login+"'");
							rsPuntos.next();%>
							<tr>
								<td><img class="imgUser" src="<%=rs.getString("imagen")%>"/></td>
								<td><a title="ver informacion" href="FrontControllerAdmin?accion=infoClienteAdmin&nombre=<%=rs.getString("login")%>"><%=rs.getString("nombre")%></a></td>
								<td><%=rs.getString("apellido")%></td>
								<td><%=rs.getString("fecha_nac")%></td>
								<td><%=rs.getString("mail")%></td>
								<td><%=rsPuntos.getString("puntos")%></td>
								<td><%=rsPuntos.getString("tipo_cuenta")%></td>
							</tr>
						<%} %>	
				</table>
			</div>
			<div class="pieCentrado">
				<img class="pieImagen" src="imagenes/pie.png" alt="pie"/>
			</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>