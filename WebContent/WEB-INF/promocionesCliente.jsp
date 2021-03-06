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
				<a href="FrontControllerCliente?accion=homeCliente"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
				<img class="gif" src="imagenes/gifjuega.gif" alt="GIF"/>
			</div><!-- FIN CABECERA-->
			<div class="menu">
				<ul class="listaMenuCliente">
					<a class="enlace" href="FrontControllerCliente?accion=homeCliente"><li>Home</li></a>
					<a class="enlace" href="FrontControllerCliente?accion=jugarCliente"><li>Jugar</li></a>
					<a class="enlace" href="FrontControllerCliente?accion=tiendaCliente"><li>Tienda</li></a>
					<a class="enlace_marcado" href="#"><li class="marcado">Promociones</li></a>
					<a class="enlace" href="Salir"><li>Log out</li></a>
				</ul>
			</div>
			
			<div id="promos">
				<div id="sinpromos">
					<h1>Sin promociones disponibles aun</h1>
				</div>
			</div>
			
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png">
			</div>
		</div><!--FIN CONTENEDOR-->
</body>
</html>