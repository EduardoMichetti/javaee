/**
 * Confirmação de exclusão de um contato
* @author Professor José de Assis
* @param idcon
 */

function confirmar(idcon) {
	//criar uma variável
	//confirm: abre uma caixa de diálogo
	let resposta = confirm("Confirma a exclusão deste contato ?")
	if (resposta === true) {
		//alert(idcon)
		//window.location.href é usado para fazer um redirecionamento, estou redirecionando delete 
		//para o servlet passando idcon como parametro
		window.location.href = "delete?idcon=" + idcon
	}

}