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
					<img class="gif" src="imagenes/gifjuega.gif" alt="GIF"/>
			</div><!-- FIN CABECERA-->
			<div class="menu">
				<ul class="listaMenuCliente">
					<a class="enlace" href="FrontControllerCliente?accion=homeCliente"><li>Home</li></a>
					<a class="enlace" href="FrontControllerCliente?accion=jugarCliente"><li>Jugar</li></a>
					<a class="enlace_marcado" href="#"><li class="marcado">Tienda</li></a>
					<a class="enlace" href="FrontControllerCliente?accion=promocionesCliente"><li>Promociones</li></a>
					<a class="enlace" href="Salir"><li>Log out</li></a>
				</ul>
			</div><!-- FIN MENU-->
			
			<div class="informacion">
				<div id="formularioComprar">
					<form action="ComprarPuntos" method="POST">
					<table class="tablasform">
						<tr>
							<td colspan="2" class="titulo" style="text-align: center;">Comprar</td>
						</tr>
						<tr>
							<td class="celda_izquierda">Puntos:</td>
							<td class="celda_derecha">
									<select name="puntos" multiple> 
									<option value=100> 100 puntos - 10 Euros</option>
									<option value=300> 300 puntos - 30 Euros</option>
									<option selected value=500> 500 puntos - 45 Euros</option>
									<option value=1000> 1000 puntos - 90 Euros</option>
									</select> 
							</td>
						</tr>
						<tr>
							<td class="celda_izquierda">Usuario:</td><td class="celda_derecha"><input class="inp" type="text" name="tarjeta"/></td>
						</tr>
						<tr>
							<td class="celda_izquierda">Contraseña:</td><td class="celda_derecha"><input class="inp" type="text" name="titular"/></td>
						</tr>
						<tr>
							<td class="celda_izquierda boton_enviar"><input class="boton" type="submit" name="enviar" value="Enviar"/></td><td class="celda_derecha"><input class="boton" type="reset" name="borrar" value="Borrar"/></td>
						</tr>					
					</table>
					</form>
				</div>

				
				<div id="formularioVender">
					<form action="VenderPuntos" method="POST">
						<table class="tablasform">
							<tr>
								<td colspan="2" class="titulo" style="text-align: center;">Vender</td>
							</tr>
							<tr>
								<td class="celda_izquierda">Puntos:</td>
								<td class="celda_derecha">
									<input class="inp" type="text" name="puntos"/>		
								</td>
							</tr>
							<tr>
								<td class="celda_izquierda">Usuario:</td><td class="celda_derecha"><input class="inp" type="text" name="tarjeta"/></td>
							</tr>
							<tr>
								<td class="celda_izquierda">Contraseña:</td><td class="celda_derecha"><input class="inp" type="text" name="titular"/></td>
							</tr>
							<tr>
								<td class="celda_izquierda boton_enviar"><input class="boton" type="submit" name="enviar" value="Enviar"/></td><td class="celda_derecha"><input class="boton" type="reset" name="borrar" value="Borrar"/></td>
							</tr>					
						</table>
					</form>
				</div>
				
				<div id="cambiarpremium">
					<form action="ComprarPremium" method="POST">
					<table class="tablasform">
						<tr>
							<td colspan="2" class="titulo" style="text-align: center;">Cambiar a premium</td>
						</tr>
						<tr>
							<td class="celda_izquierda">Premium:</td>
							<td class="celda_derecha">
							<input class="inp" type="text" name="premium" value="100" size="5" disabled/> Puntos
							</td>
							
						</tr>
						<tr>
							<td class="celda_izquierda boton_enviar"><input class="boton" type="submit" name="Enviar" value="Comprar"/></td>
						</tr>	
					</table>
					</form>
				</div>
			</div><!--FIN INFORMACION-->
			
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png">
			</div>
		</div><!--FIN CONTENEDOR-->
</body>
</html>