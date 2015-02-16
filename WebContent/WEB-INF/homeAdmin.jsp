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
		<link rel="StyleSheet" type="text/css" href="estilos/estiloHome.css"/>
		<script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
		
		<script>
			google.load('visualization', '1', {packages: ['corechart']});
			google.setOnLoadCallback(graficoTipoCuenta);
			function graficoTipoCuenta() {
				
					<% 
					DameConexion instancia = DameConexion.getInstancia();
					Connection conexion = instancia.getConexion();
					
					Statement oStmt = conexion.createStatement();
					
					ResultSet rs = oStmt.executeQuery("SELECT count(1) as cuenta "+ 
							"FROM Cuenta WHERE tipo_cuenta = 1");
					rs.next();
					int normales = Integer.parseInt((String) rs.getString("cuenta"));
					
					rs = oStmt.executeQuery("SELECT count(1) as cuenta "+ 
							"FROM Cuenta WHERE tipo_cuenta = 2");
					rs.next();
					int premium = Integer.parseInt((String) rs.getString("cuenta"));
					%>
				
				var normales = <%=normales%>
				var premium = <%=premium%>
			    var data = google.visualization.arrayToDataTable([
			      ['Cuenta', 'Clientes'],
			      ['Normal', normales],
			      ['Premium', premium]
			    ]);
	
			    var options = {
			      	title: 'Tipo de cuentas',
			      	backgroundColor: 'transparent',
			      	legend: {position: 'top', textStyle: {color: 'white', fontSize: 14}},
			      	width: 500,
			      	height: 400
			    };
	
			    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	
			    chart.draw(data, options);
			  }
		</script>
		<script>				
			    google.load('visualization', '1.1', {packages: ['bar']});
				google.setOnLoadCallback(drawChart);

					function drawChart() {
						<% 				
						Statement oStmtPartidasporjuego = conexion.createStatement();
						
						ResultSet rs2 = oStmtPartidasporjuego.executeQuery("SELECT count(1) as partidasHighestCard FROM Balance WHERE id_juego=3");
						rs2.next();
						int partidasHighestCard = Integer.parseInt((String) rs2.getString("partidasHighestCard"));
						
						rs2 = oStmtPartidasporjuego.executeQuery("SELECT count(1) as partidasBlackJack FROM Balance WHERE id_juego=1");
						rs2.next();
						int partidasBlackJack = Integer.parseInt((String) rs2.getString("partidasBlackJack"));
						
						rs2 = oStmtPartidasporjuego.executeQuery("SELECT count(1) as partidasDados FROM Balance WHERE id_juego=2");
						rs2.next();
						int partidasDados = Integer.parseInt((String) rs2.getString("partidasDados"));
											
					%>
					var partidasBlackJack = <%=partidasBlackJack%>
					var partidasHighestCard = <%=partidasHighestCard%>
					var partidasDados = <%=partidasDados%>
					
					var data = google.visualization.arrayToDataTable([
						['Juego', 'Partidas'],
					]);
					
					var data = new google.visualization.DataTable();
					data.addColumn('string', 'Juego');
					data.addColumn('number', 'Partidas Dados');
					data.addColumn('number', 'Partidas Blackjack');
					data.addColumn('number', 'Partidas HighestCard');
					
					 
					data.addRows([
						['Partidas Jugadas', 1, 2, 3],
					]);

					  var options = {
						width: 1000,
						height: 563,
						hAxis: {
						  title: 'Juegos',
						},
						vAxis: {
						  title: 'Numero de partidas'
						}
					  };

					  var chart = new google.charts.Bar(document.getElementById('barChart'));

					  chart.draw(data, google.charts.Bar.convertOptions(options));
					}
		</script>
	</head>
	
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="home.html"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
				<p>Bienvenido</p>
			</div><!-- FIN CABECERA-->
			<div class="menu">
				<ul class="listaMenuAdmin">
					<a class="enlace_marcado" href="#"><li class="marcado">Home</li></a>
					<a class="enlace" href="FrontControllerAdmin?accion=clientesAdmin"><li>Clientes</li></a>
					<a class="enlace" href="FrontControllerAdmin?accion=noticiasAdmin"><li>Noticias</li></a>
					<a class="enlace" href="Salir"><li>Log out</li></a>
				</ul>
			</div>
			
			<div class="informacion">
				<table>
				<tr>
					<td>
						<%
							rs = oStmt.executeQuery("SELECT login "+ 
									"FROM Usuario WHERE tipo_user = 2");
							rs.next();
							String admin = (String) rs.getString("login");
						%>
						<p>Hola <%=admin%></p>
					</td>
				</tr>
					<tr>
						<td>
						 	<div id="piechart" style="width: 900px; height: 500px;"></div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="barChart" style="width: 900px; height: 500px;"> </div>
						</td>
					</tr>
				</table>
			</div>
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png">
			</div>
			
		</div><!--FIN CONTENEDOR-->
	</body>
</html>