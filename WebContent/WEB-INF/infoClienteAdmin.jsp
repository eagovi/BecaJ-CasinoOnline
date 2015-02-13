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
					<a href="FrontControllerAdmin?accion=homeAdmin"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
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
				<div class="tresTablas">
					<table class="tablaDatosCliente">
						<%
							DameConexion instancia = DameConexion.getInstancia();
							Connection conexion = instancia.getConexion();
							
							
							String login = (String)request.getAttribute("loginCliente");
							Statement oStmt = conexion.createStatement();
							Statement oStmtPuntos = conexion.createStatement();
							
							ResultSet rsPuntos = null;
							ResultSet rs = oStmt.executeQuery("SELECT login, nombre, apellido, fecha_nac, mail, imagen FROM Cliente WHERE login = '"+login+"'");
							rsPuntos = oStmtPuntos.executeQuery("SELECT c.puntos, c.tipo_cuenta, t.nombre FROM Cuenta c, tipo t WHERE c.tipo_cuenta=t.tipo_cuenta AND login='"+login+"'");
						
							rsPuntos.next();
							
							String tipoCuenta = rsPuntos.getString("nombre");
							 
							
							Statement oStmtBorrado = conexion.createStatement();
							
							ResultSet rsBorrado = oStmtBorrado.executeQuery("SELECT borrar FROM Usuario WHERE login='"+login+"'");
							
							rsBorrado.next();
							
							String borrado = rsBorrado.getString("borrar");
							
							if(rs.next()) {
						%>
							<tr>
								<td colspan="2" class="cabezaTabla">DATOS CLIENTE</td>
							</tr>
							<tr>
								<td class="subrayado">Nombre usuario</td>
								<td><%=login%></td>
							</tr>
							<tr>
								<td class="subrayado">Imagen</td>
								<td><img class="imgUser" src="<%=rs.getString("imagen")%>"/></td>
							</tr>
							<tr>
								<td class="subrayado">Nombre</td>
								<td><%=rs.getString("nombre")%></td>
							</tr>
							<tr>
								<td class="subrayado">Apellido</td>
								<td><%=rs.getString("apellido")%></td>
							</tr>
							<tr>
								<td class="subrayado">Fecha de nacimiento</td>
								<td><%=rs.getString("fecha_nac").substring(0, 10)%></td>
							</tr>
							<tr>
								<td class="subrayado">Mail</td>
								<td><%=rs.getString("mail")%></td>
							</tr>
							<tr>
								<td class="subrayado">Puntos</td>
								<td><%=rsPuntos.getString("puntos")%></td>
							</tr>
							<tr>
								<td class="subrayado">Tipo de cuenta</td>
								<td><%=tipoCuenta%></td>
							</tr>
							<%
							if(borrado.equals("0")){
							%>
							<tr>
								<td class="boton_enviar">
									<form action="BorrarCliente" method="GET">
										<input type="submit" name="borrar" value="Borrar usuario"/>
										<input type="hidden" name="usuarioBorrar" value="<%=login %>"/>
									</form>
								</td>
							<%if(tipoCuenta.equals("normal")){ %>
								<td>
									<form action="HacerPremium" method="GET">
										<input type="submit" name="premium" value="Hacer premium"/>
										<input type="hidden" name="usuarioPremium" value="<%=login %>"/>
									</form>
								</td>
								<%} else{ %>
								<td>
									<form action="QuitarPremium" method="GET">
										<input type="submit" name="premium" value="Quitar premium"/>
										<input type="hidden" name="usuarioNormal" value="<%=login %>"/>
									</form>
								</td>
								<%} %>
							</tr>
							<%}
							else {%>
							<tr>
								<td colspan="2" style="text-align:center;">
									<form action="ReactivarCliente" method="GET">
										<input type="submit" name="reactivar" value="Reactivar usuario"/>
										<input type="hidden" name="usuarioReactivar" value="<%=login %>"/>
									</form>
								</td>
							</tr>
							<%}
							}
							%>
							</table><!--FIN TABLA DATOS CLIENTE-->
				</div><!--cierro el div tresTablas-->
				
				<div class="tresTablas">
					<table class="tablaPuntos">
					<%
						Statement oStmtCaja = conexion.createStatement();
						Statement oStmtCompraPuntos = conexion.createStatement();
						Statement oStmtVentaPuntos = conexion.createStatement();
						ResultSet rsCompraPuntos = null;
						ResultSet rsVentaPuntos = null;
						ResultSet rsClienteCaja = null;
						
						rsClienteCaja = oStmtCaja.executeQuery("SELECT id_transaccion FROM caja WHERE login='"+login+"'");
						rsClienteCaja.next();
						int idTransaccion = Integer.parseInt(rsClienteCaja.getString("id_transaccion"));
						
						rsCompraPuntos = oStmtCompraPuntos.executeQuery("SELECT fecha, puntos FROM compra_puntos WHERE id_transaccion="+idTransaccion);
						rsVentaPuntos = oStmtVentaPuntos.executeQuery("SELECT fecha, puntos FROM venta_puntos WHERE id_transaccion="+idTransaccion);
						
						
						%>
						<tr>
							<td colspan="2" class="cabezaTabla">HISTORIAL DE PUNTOS</td>
						</tr>
						<tr>
							<td>
								<table class="tablaPuntosComprados">
									<tr>
										<td class="cabezaTabla" colspan="2">Comprados</td>
									</tr>
									<tr>
										<td class="subrayado">Fecha</td>
										<td class="subrayado">Puntos</td>
									</tr>
									<%
									while(rsCompraPuntos.next()){
									 %>
									<tr>
										<td><%=rsCompraPuntos.getString("fecha") %></td>
										<td><%=rsCompraPuntos.getString("puntos") %></td>
									</tr>
								<%} %>
								</table>
							</td>
							<td>
								<table class="tablaPuntosVendidos">
									<tr>
										<td class="cabezaTabla" colspan="2">Vendidos</td>
									</tr>
									<tr>
										<td class="subrayado">Fecha</td>
										<td class="subrayado">Puntos</td>
									</tr>
									<%
									while(rsVentaPuntos.next()){
									%>
									<tr>
										<td><%=rsVentaPuntos.getString("fecha") %></td>
										<td><%=rsVentaPuntos.getString("puntos") %></td>
									</tr>
								<%}%>
								</table>
							</td>
						</tr>
					</table><!--FIN TABLA PUNTOS-->
				</div><!--cierro el div tresTablas-->
				
				<div class="tresTablas">
					<table class="tablaEstadisticas">
						<tr>
							<td colspan="2" class="cabezaTabla">ESTADISTICAS</td>
						</tr>
					<%
						Statement oStmtBalance = conexion.createStatement();
						Statement oStmtBalance2 = conexion.createStatement();
						Statement oStmtEstadisticas = conexion.createStatement();
												
						ResultSet rsBalance = null;
						ResultSet rsBalance2 = null;
						ResultSet rsEstadisticas = null;
						
						
						rsClienteCaja = oStmtCaja.executeQuery("SELECT id_balance FROM caja WHERE login='"+login+"'");
						rsClienteCaja.next();
						String idBalance = rsClienteCaja.getString("id_balance");
						rsBalance = oStmtBalance.executeQuery("SELECT puntos, fecha, id_juego FROM balance WHERE id_balance="+idBalance);
						rsBalance2 = oStmtBalance2.executeQuery("SELECT b.puntos, b.fecha, b.id_juego, j.nombre FROM balance b, juego j WHERE b.id_juego=j.id_juego AND id_balance="+idBalance);
						
						if(rsBalance2.next()){
							String nombreJuego = rsBalance2.getString("nombre");
						
															
						
						while(rsBalance.next()){
						%>
						<tr>
							<td class="subrayado">Puntos</td>
							<td><%=rsBalance.getString("puntos")%></td>
						</tr>
						<tr>
							<td class="subrayado">Fecha</td>
							<td><%=rsBalance.getString("fecha")%></td>
						</tr>
						<tr>
							<td class="subrayado">Juego</td>
							<td><%=nombreJuego %></td>
						</tr>
						<tr>
							<td><br></td>
						</tr>						
						<%}
						}
						else{%>
						<tr>
							<td colspan="2" style="text-align:center;">No hay partidas jugadas</td>
						</tr>
						<%}%>
					</table>	
				</div><!--cierro el div tresTablas-->
				<div class="enlaceVolver">
					<a href="FrontControllerAdmin?accion=clientesAdmin"><h3>Volver</h3></a>
				</div>
			</div>
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png" alt="pie"/>
			</div>
		</div><!--FIN CONTENEDOR-->
	</body>
</html>