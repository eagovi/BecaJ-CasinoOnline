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
		<title>Jugar</title>
		<link rel="StyleSheet" type="text/css" href="estilos/estiloCliente.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script>
		$(document).ready(function() {
			var imagen = $('.imagenJuego');
			var descripcion = $('.descripcionJuego');
			var titulo = $('.tituloJuego');
			var mostrar0 = false;
			var mostrar1 = false;
			var mostrar2 = false;
			
			
			titulo.eq(0).click(function(){
			if(mostrar0==false){
					descripcion.eq(0).removeClass('invisible');
					imagen.eq(0).addClass('invisible');
					mostrar0=true;
				}
			else{
					descripcion.eq(0).addClass('invisible');
					imagen.eq(0).removeClass('invisible');
					mostrar0=false;
				}
			});
			
			titulo.eq(1).click(function(){
			if(mostrar1==false){
					descripcion.eq(1).removeClass('invisible');
					imagen.eq(1).addClass('invisible');
					mostrar1=true;
				}
			else{
					descripcion.eq(1).addClass('invisible');
					imagen.eq(1).removeClass('invisible');
					mostrar1=false;
				}
			});
			
			titulo.eq(2).click(function(){
				if(mostrar2==false){
					descripcion.eq(2).removeClass('invisible');
					imagen.eq(2).addClass('invisible');
					mostrar2=true;
				}
			else{
					descripcion.eq(2).addClass('invisible');
					imagen.eq(2).removeClass('invisible');
					mostrar2=false;
				}
			});
			
		});
	</script>
	</head>
	
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="homeCliente.jsp"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
				<img class="gif" src="imagenes/gifjuega.gif" alt="GIF"/>
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
								<%=rs.getString("nombre")%><span class="tituloJuego"> - Reglas</span><br>
								<a href="<%=rs.getString("nombre")%>Servlet"><img class="imagenJuego" src="<%=rs.getString("imagen")%>"></a>
								<div class="descripcionJuego invisible"><p><%=rs.getString("descripcion")%></p></div>								
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