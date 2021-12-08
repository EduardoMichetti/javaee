package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/Select", "/update", "/delete",
		"/report" }) /* requisição feita no index, tratada aqui. */
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * //teste de conexão dao.testeConexao();
		 */
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {// se o conteúdo da variável action for igual a /main
			contatos(request, response);
		} else if (action.equals("/insert")) {// ação criada dentro de novo.html FORM
			novoContato(request, response);
		} else if (action.equals("/Select")) {// ação criada dentro de agenda.jsp FORM
			listarContato(request, response);
		} else if (action.equals("/update")) {// ação criada dentro de editar.jsp FORM
			editarContato(request, response);
		} else if (action.equals("/delete")) {// ação criada dentro de editar.jsp FORM
			removerContato(request, response);
		} else if (action.equals("/report")) {// ação criada dentro de editar.jsp FORM
			gerarRelatorio(request, response);
		} else {// se não achar nada vai chamar o index
			response.sendRedirect("index.html");
		}

	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp"); // vai redirecionar a requisição ao
		// documento agenda.jsp
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
//		// teste de recebimento da lista
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getIdcon());
//			System.out.println(lista.get(i).getNome());
//			System.out.println(lista.get(i).getFone());
//			System.out.println(lista.get(i).getEmail());
//		} 
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista); // setar um atributo do doc JSP com a lista
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp"); // Despachar a lista para o documento JSP,
																			// RequestDipatcher é uma classe modelo que
																			// trabalha requisições e respostas no
																			// servlet
		rd.forward(request, response);// encaminha o objeto lista ao documento agenda.jsp
	}

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//			// TESTE DE RECEBIMENTO
//			System.out.println(request.getParameter("nome")); //parametros do formulario sao identificados atraves de NAME
//			System.out.println(request.getParameter("fone"));
//			System.out.println(request.getParameter("email"));
		// setar as varáveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato);
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será selecionado.
		String param_idcon = request.getParameter("idcon");
		// setar a variável JavaBeans
		contato.setIdcon(param_idcon);
		// Executar o método selecionarContato da classe DAO
		dao.selecionarContato(contato);
//		//teste de recebimento
//		System.out.println(contato.getIdcon());
//		System.out.println(contato.getNome());
//		System.out.println(contato.getFone());
//		System.out.println(contato.getEmail());

		// Setar, preecher os atibutos do forumalio com o conteudo do JavaBEans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar .jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// teste de recebimento
//		System.out.println(request.getParameter("idcon"));
//		System.out.println(request.getParameter("nome")); // parametros do formulario sao identificados atraves de NAME
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));
		// setar as variáveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// executar o metodo alterarContato
		dao.alterarContato(contato);
		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover um contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do contato a ser excluido (validador.js)
		String idcon = request.getParameter("idcon");
		// teste de recebimento
		// System.out.println(idcon);

		// setar a variável idcon JavaBeans
		contato.setIdcon(idcon);
		// executar o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatório em PDF
		protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Document documento = new Document();//este objeto será usado para acessar os atributos e métodos da biblioteca itexpdf
			try {
				// tipo de conteudo
				response.setContentType("apllication/pdf"); //estamos informando que o tipo de resposta será um documento pdf
				// nome do documento
				response.addHeader("Content-Disposition", "inline; filename=contatos.pdf");
				//criar o documento, recurso da biblioteca itextpdf
				PdfWriter.getInstance(documento, response.getOutputStream());
				//abrir o documento -> para gerar o conteúdo
				documento.open();
				documento.add(new Paragraph("Lista de contatos:"));
				documento.add(new Paragraph(" "));
				//criar uma tabela, os relatório são dinâmicos então preciso criar um outro objeto da biblioteca que permite trabalhar com tabelas de forma dinâmica
				PdfPTable tabela = new PdfPTable(3); //significa que a tabela tem 3 colunas
				//Cabeçalho
				PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				//popular a tabela com os contatos
				ArrayList<JavaBeans> lista = dao.listarContatos(); //mesmo utilizado em LISTAR CONTATOS
				for(int i = 0; i < lista.size(); i++) {
					tabela.addCell(lista.get(i).getNome());
					tabela.addCell(lista.get(i).getFone());
					tabela.addCell(lista.get(i).getEmail());
				}
				
				documento.add(tabela);				
				documento.close();
			} catch (Exception e) {
				System.out.println(e);
				documento.close();
			}
		}
}
