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
					<form action="" method="POST">
						<tr>
							<td class="tablaTop">Titular:</td>
							<td><input type="text" maxlength="40" name="titularNoticia"/></td>
						</tr>
						<tr>
							<td class="tablaTop">URL de imagen:</td>
							<td><input type="text" maxlength="150" name="imagenNoticia"/></td>
						</tr>
						<tr>
							<td class="tablaTop">Contenido:</td> 
							<td><textarea cols="40" rows="8" name="contenidoNoticia"></textarea></td>
						</tr>
						<tr>
							<td><input class="boton boton_enviar" type="submit" name="enviar" value="Enviar"/></td> 
							<td><input class="boton" type="reset" name="borrar" value="borrar"/></td>
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