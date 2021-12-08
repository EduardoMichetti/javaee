<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang=>
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<!-- é uma tag do html que acrescenta um ícone ao favorito -->
<link rel="stylesheet" href="style.css">
<!-- cria um link entre o documento html e a folha de estilo css -->
</head>
<body>

	<!--
table: Tabela / tr: linhas da tabela / td: coluna da tabela
type text: desenha uma caixa de texto
placeholder: insere um texto de marca dagua dentro da caixa de texto
readonly bloqueia a caixa de texto
 -->

	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
				<!-- no html css podemos usar um estilo com ID ou CLASS a diferença deles é que o ID pode ser usado uma única vez já classe pode ser reutilziada -->
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="Caixa2" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
	<!-- Lincar o documento html com o documento javascript -->
</body>
</html>