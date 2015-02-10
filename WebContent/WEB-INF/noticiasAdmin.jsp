<!DOCTYPE html>
<html>
	</head>
		<meta charset="ISO-8859-1">
		<title>Noticias</title>
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
				<a class="enlace" href="FrontControllerAdmin?accion=clientesAdmin"><li>Clientes</li></a>
				<a class="enlace_marcado" href="#"><li class="marcado">Noticias</li></a>
				<a class="enlace" href="Salir"><li>Log out</li></a>
			</ul>
			</div>
			<div class="informacionNoticias">
				<table class="tablaNoticias">
					<form action="Noticias" method="POST">
						<tr>
							<td class="celda_izq">Titular:</td>
							<td class="celda_der"><input type="text" maxlength="40" size="38" name="titularNoticia"/></td>
						</tr>
						<tr>
							<td class="celda_izq">URL de imagen:</td>
							<td class="celda_der"><input type="text" maxlength="150" size="38" name="imagenNoticia"/></td>
						</tr>
						<tr>
							<td class="celda_izq">Contenido:</td> 
							<td class="celda_der"><textarea cols="40" rows="6" name="contenidoNoticia"></textarea></td>
						</tr>
						<tr>
							<td class="celda_izq boton_enviar"><input class="boton" type="submit" name="enviar" value="Enviar"/></td> 
							<td class="celda_der"><input class="boton" type="reset" name="borrar" value="borrar"/></td>
						</tr>
					</form>
				</table>
			</div>
			<div class="pieCentrado">
				<img class="pieImagen" src="imagenes/pie.png" alt="pie"/>
			</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>