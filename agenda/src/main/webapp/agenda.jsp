<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos"); //Aqui recebemos o objeto lista
// 	//Testar o recebimento
// 	for (int i=0; i < lista.size(); i++){
// 		out.println(lista.get(i).getIdcon());
// 		out.println(lista.get(i).getNome());
// 		out.println(lista.get(i).getFone());
// 		out.println(lista.get(i).getEmail());
// 	}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<!-- icone na pagina -->
<link rel="stylesheet" href="style.css">
<!-- chama a folha de estilo para ser usada aqui -->

</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo Contato</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<!-- TABELA -->
		<thead>
			<!-- CABEÇALHO DA TABELA -->
			<tr>
				<!-- CRIAR LINHA -->
				<th>Id</th>
				<!-- CRIAR COLUNA -->
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>

		</thead>
		<tbody>
			<!-- CORPO DA TABELA -->
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdcon()%></td>
				<!-- CRIAR COLUNA -->
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getFone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href="Select?idcon=<%=lista.get(i).getIdcon()%>"
					class="Botao1">Editar</a></a><!--Select? o interrogação é para pegar parametro-->
					<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)"
					class="Botao2">Excluir</a></a>
					</td> 
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>