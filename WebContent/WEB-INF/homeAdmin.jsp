<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.casino.dataService.DameConexion"%>
<%@page import="com.casino.dao.Consultas" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Home</title>
		<link rel="StyleSheet" type="text/css" href="estilos/estiloHome.css"/>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript">
		    google.load("visualization", "1", {packages:["corechart"]});
		    google.setOnLoadCallback(drawChart);
		    function drawChart() {
		    	<%
				int partidasHighestCard = Consultas.getInstancia().numeroPartidas(3);
				int partidasBlackJack = Consultas.getInstancia().numeroPartidas(1);
				int partidasDados =  Consultas.getInstancia().numeroPartidas(2);			
				%>
				var partidasBlackJack = <%=partidasBlackJack%>
				var partidasHighestCard = <%=partidasHighestCard%>
				var partidasDados = <%=partidasDados%>
		    	
			      var data = google.visualization.arrayToDataTable([
			        ["Element", "Numero", { role: "style" } ],
			        ["Dados", partidasDados, "grey"],
			        ["Carta alta", partidasHighestCard, "gold"],
			        ["BlackJack", partidasBlackJack, "#394bf2"],
			      ]);
			
			      var view = new google.visualization.DataView(data);
			      view.setColumns([0, 1,
			                       { calc: "stringify",
			                         sourceColumn: 1,
			                         type: "string",
			                         role: "annotation" },
			                       2]);
			
			      var options = {
			        title: "Numero de partidas",
			        backgroundColor: 'transparent',
			        width: 450,
			        height: 300,
			        bar: {groupWidth: "95%"},
			        legend: { position: "none", textStyle: {color: 'black', fontSize: 14}},
			      };
			      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
			      chart.draw(view, options);
	  		}
	  </script>
	  <script>
			google.setOnLoadCallback(graficoTipoCuenta);
			function graficoTipoCuenta() {
				<% 
				int normales = Consultas.getInstancia().numeroCuentas(1);
				int premium = Consultas.getInstancia().numeroCuentas(2);
				%>
				var normales = <%=normales%>
				var premium = <%=premium%>
			    var data1 = google.visualization.arrayToDataTable([
			      ['Cuenta', 'Clientes'],
			      ['Normal', normales],
			      ['Premium', premium]
			    ]);
	
			    var options = {
			      	title: 'Tipo de cuentas',
			      	backgroundColor: 'transparent',
			      	legend: {position: 'rigth', textStyle: {color: 'black', fontSize: 14}},
			      	width: 450,
			      	height: 300
			    };
	
			    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
	
			    chart.draw(data1, options);
			  }
			
		</script>
	</head>
	
	<body>
		<div class="contenedor">
			<div class="cabecera">
				<a href="FrontControllerAdmin?accion=homeAdmin"><img class="logo" src="imagenes/logo.png" alt="logo"/></a>
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
			
				<table class="tablaDatosAdmin">
					<tr class="cabezaTabla" style="font-family:october;">
						<td colspan="2">Hola administrador</td>
					</tr>
					<tr>
						<td>Numero de clientes: </td>
						<td><%=Consultas.getInstancia().totalUsuarios()%></td>
					</tr>
					<tr>
						<td>Puntos comprados este mes: </td>
						<td><%=Consultas.getInstancia().totalUsuarios()%></td>
					</tr>
					<tr>
						<td>Puntos vendido este mes: </td>
						<td><%=Consultas.getInstancia().totalUsuarios()%></td>
					</tr>
					<tr>
						<td>Ganados este mes con Blackjack: </td>
						<td><%=Consultas.getInstancia().totalUsuarios()%></td>
					</tr>
					<tr>
						<td>Ganados este mes con HighestCard: </td>
						<td><%=Consultas.getInstancia().totalUsuarios()%></td>
					</tr>
					
				</table>
				<div class="graficos">
					<div id="piechart" style="width: 600px; height: 300px;"></div>

					<div id="columnchart_values" style="width: 600px; height: 300px;"> </div>
				</div>
						
			</div>
			<div class="pie">
				<img class="pieImagen" src="imagenes/pie.png">
			</div>
			
		</div><!--FIN CONTENEDOR-->
	</body>
</html>