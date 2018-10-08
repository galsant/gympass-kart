<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js">	
</script>

<script type="text/javascript" type="text/javacript">
$(document).ready(function(){
    $('ul.tabs').tabs({
        swipeable : true   
    })
  });
</script>

<title>Log GP Kart</title>
</head>
<body>
	<div class="container">
		<s:form action="gerarResultado" method="post" theme="simple" enctype="multipart/form-data">
			<div class="file-field input-field row">
				<div class="btn waves-effect waves-light red lighten-2">
	       			<span>Arquivo</span><s:file name="upload" label="File"  />
	       		</div>
	       		<div class="file-path-wrapper col s4">
	       			<input class="file-path validate" type="text" placeholder="Faça upload do Log de corrida">
	       		</div>
	       		<s:submit class="waves-effect waves-light btn red lighten-2" type="submit" value="Gerar Resultado"/>
			</div>
			<div class="row">
				<s:if test="hasErrors()">
					<s:actionerror />
					<s:fielderror />
				</s:if>
			</div>
			<div class="row">
				<div class="col s12">
					<ul id="tabs-swipe-demo" class="tabs">
						<li class="tab col s3 active"><a href="#test-swipe-1">Classificação</a></li>
						<li class="tab col s3"><a href="#test-swipe-2">Melhor volta</a></li>
						<li class="tab col s3"><a href="#test-swipe-3">Melhores voltas</a></li>
						<li class="tab col s3"><a href="#test-swipe-4">Velocidade média</a></li>
						
					</ul>
				</div>
				<div class="teste"></div>
				<div id="test-swipe-1" class="col s12">
					<table class="highlight">
						<thead>
							<tr>
								<th>Posição</th>
								<th>Código Piloto</th>
								<th>Nome Piloto</th>
								<th>Voltas Completadas</th>
								<th>Tempo Total</th>
								<th>Diferença para Líder</th>
								<th></th>
							</tr>
						</thead>

						<tbody id="fundo1">
							<s:iterator value="resultadoFinal">
							<tr>
								<td><s:property value="classificacao" /></td>
								<td><s:property value="piloto.id" /></td>
								<td><s:property value="piloto.nome" /></td>
								<td><s:property value="voltas" /></td>
								<td><s:date name="tempoTotal.toDate" format="mm:ss:SSS"/></td>
								<td><s:date name="diferenca.toDate" format="+mm:ss:SSS"/></td>
								<td><s:if test="!completouProva">Desclassificado</s:if></td>
							</tr>
							</s:iterator>
							
						</tbody>
					</table>
				</div>
				<div id="test-swipe-2" class="col s12">
				<table class="highlight">
					<thead>
						<tr>
							<th>Piloto</th>
							<th>Número da volta</th>
							<th>Tempo</th>
							<th>Velocidade média</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td><s:property value="corrida.melhorVolta.piloto.nome" /></td>
							<td><s:property value="corrida.melhorVolta.numeroVolta" /></td>
							<td><s:date name="corrida.melhorVolta.tempo.toDate" format="mm:ss:SSS"/></td>
							<td><s:number name="corrida.melhorVolta.velocidadeMedia" maximumFractionDigits="3" /> Km/h</td>
							</tr>
					</tbody>
				</table>
			</div>
				<div id="test-swipe-3" class="col s12">
					<table class="highlight">
						<thead>
							<tr>
								<th>Piloto</th>
								<th>Número da volta</th>
								<th>Tempo</th>
							</tr>
						</thead>

						<tbody id="fundo2">
							<s:iterator value="melhoresVoltas">
								<tr>
									<td><s:property value="piloto.nome" /></td>
									<td><s:property value="numeroVolta" /></td>
									<td><s:date name="tempo.toDate" format="mm:ss:SSS"/></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div id="test-swipe-4" class="col s12">
					<table class="highlight">
						<thead>
							<tr>
								<th>Piloto</th>
								<th>Velocidade média</th>
							</tr>
						</thead>

						<tbody id="fundo3">
							<s:iterator value="resultadoFinal">
								<tr>
									<td><s:property value="piloto.nome" /></td>
									<td><s:number name="velocidadeMedia"  maximumFractionDigits="3" /> Km/h</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>