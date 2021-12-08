/**
 * Validação de formulário
 * @author José de Assis
 */

function validar(){
	//alert('teste')//testar se o doc html esta lincado corretamente
	//recebe os campos obrigatórios
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	if (nome === ""){//verificar se nome foi preenchido
		alert('Preencha o campo Nome')
		frmContato.nome.focus() //coloca o cursor no campo nome
		return false
	} else if (fone === ""){//verificar se nome foi preenchido
		alert('Preencha o campo Fone')
		frmContato.fone.focus() //coloca o cursor no campo nome
		return false
	} else {
		document.forms["frmContato"].submit()//se tudo estiver ok ele vai submeter os dados para camada CONTROLLER
	}
	
}