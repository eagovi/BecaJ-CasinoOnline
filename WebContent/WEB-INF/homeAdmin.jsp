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
		<link rel="StyleSheet" type="text/css" href="../estilos/estiloHome.css"/>
	</head>
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="home.html"><img class="logo" src="../imagenes/logo.png" alt="logo"/></a>
					<p>Bienvenido</p>
			</div><!-- FIN CABECERA-->
			<div class="menu">
			<ul class="listaMenuAdmin">
				<a class="enlace_marcado" href="homeAdmin.jsp"><li class="marcado">Home</li></a>
				<a class="enlace" href="clientes.jsp"><li>Clientes</li></a>
				<a class="enlace" href="#"><li>Noticias</li></a>
				<a class="enlace" href="#"><li>Log out</li></a>
			</ul>
			</div>
			
		</div><!--FIN CONTENEDOR-->
	</body>
</html>