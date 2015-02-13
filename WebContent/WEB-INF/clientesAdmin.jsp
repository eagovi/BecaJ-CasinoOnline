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
					<a class="enlace_marcado" href="#"><li class="marcado">Clientes</li></a>
					<a class="enlace" href="FrontControllerAdmin?accion=noticiasAdmin"><li>Noticias</li></a>
					<a class="enlace" href="Salir"><li>Log out</li></a>
				</ul>
			</div>
			
			<div class="informacion">
				<table class="tablaDatos">
						
												
						<%
						DameConexion instancia = DameConexion.getInstancia();
						Connection conexion = instancia.getConexion();
						
						Statement oStmt = conexion.createStatement();
						Statement oStmtPuntos = conexion.createStatement();
						Statement oStmtBorrados = conexion.createStatement();
						
						
						ResultSet rs = oStmt.executeQuery("SELECT c.login, c.nombre, c.apellido, c.fecha_nac, c.mail, c.imagen FROM Cliente c, Usuario u WHERE c.login=u.login AND u.borrar=0");
						ResultSet rsBorrados = oStmtBorrados.executeQuery("SELECT c.login, c.nombre, c.apellido, c.fecha_nac, c.mail, c.imagen FROM Cliente c, Usuario u WHERE c.login=u.login AND u.borrar=1");
						ResultSet rsPuntos = null;
						
						int contador=0;					
						while(rs.next()) {
							
							String login = rs.getString("login");
							rsPuntos = oStmtPuntos.executeQuery("SELECT puntos, tipo_cuenta FROM Cuenta WHERE login='"+login+"'");
							rsPuntos.next();
						if(contador==0){%>
							<tr>
								<td><br></td>
							</tr>
							<tr>
								<td colspan="7" class="cabezaTabla">Usuarios Registrados</td>
							</tr>
							<tr>
								<td class="tablaTop">Imagen</td>
								<td class="tablaTop">Nombre</td>
								<td class="tablaTop">Apellido</td>
								<td class="tablaTop">Fecha de nacimiento</td>
								<td class="tablaTop">Mail</td>
								<td class="tablaTop">Puntos</td>
								<td class="tablaTop">Tipo de cuenta</td>
							</tr>
						<%contador++; 
						}
						%>
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
						
						<%
						int cont = 0;
						while(rsBorrados.next()) {
							if(cont == 0) {%>
								<tr>
								<td><br></td>
							</tr>
							<tr>
								<td colspan="7" class="cabezaTabla">Usuarios Dados de Baja</td>
							</tr>
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
							cont++;
							}
								String login = rsBorrados.getString("login");
								rsPuntos = oStmtPuntos.executeQuery("SELECT puntos, tipo_cuenta FROM Cuenta WHERE login='"+login+"'");
								rsPuntos.next();%>
							<tr>
								<td><img class="imgUser" src="<%=rsBorrados.getString("imagen")%>"/></td>
								<td><a title="ver informacion" href="FrontControllerAdmin?accion=infoClienteAdmin&nombre=<%=rsBorrados.getString("login")%>"><%=rsBorrados.getString("nombre")%></a></td>
								<td><%=rsBorrados.getString("apellido")%></td>
								<td><%=rsBorrados.getString("fecha_nac")%></td>
								<td><%=rsBorrados.getString("mail")%></td>
								<td><%=rsPuntos.getString("puntos")%></td>
								<td><%=rsPuntos.getString("tipo_cuenta")%></td>
							</tr>
							
						<%} %>
						
							
				</table>
			</div>
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png" alt="pie"/>
			</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>