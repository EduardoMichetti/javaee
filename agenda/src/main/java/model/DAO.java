package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Módulo de conexão *. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";// só vai funcionar se tiver importado o drive pra pasta LIB
	// Ip do servidor, o nome do banco de dados e o fuso horário de referencia
	/** The url. */
	// universal
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "qaz@123";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//	// teste de conexão
//	public void testeConexao(){
//		try {
//			Connection con = conectar();
//			System.out.println(con);
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	/**
 * Inserir contato.
 *
 * @param contato the contato
 */
/* CRUD CREATE */
	public void inserirContato(JavaBeans contato) {
		String sql_create = "insert into contatos (nome, fone, email) values (?,?,?)";
		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(sql_create); // P maíusculo é uma classe modelo,
																		// p minúsculo é um método
			// substituir os parametros (?) pelo conteúdo das variáveis JavaBeans, por isso
			// precisamos do PrepareStatement
			pst.setString(1, contato.getNome()); // 1 é o primeiro parametros
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	/* CRUD READ */
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um ojeto para acessar a Classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String sql_read = "select * from contatos order by nome";
		try {
			// abrir a conexão com o banco
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(sql_read); // P maíusculo é uma classe modelo,
																	// p minúsculo é um método
			ResultSet rs = pst.executeQuery();
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				// por questão didatica vou usar os mesmo nomes das variáveis javaBeans
				String str_idcon = rs.getString(1); // recebe o primeiro campo/coluna
				String str_nome = rs.getString(2);
				String str_fone = rs.getString(3);
				String str_email = rs.getString(4);
				// populando o ArrayList
				// preciso criar outro objeto para enxergar as variáveis JavaBeans, criado no
				// inicio deste método
				contatos.add(new JavaBeans(str_idcon, str_nome, str_fone, str_email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 *  CRUD UPDATE *.
	 *
	 * @param contato the contato
	 */
	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon()); // estou informando qual vai ser o parametro informado na query.
													// começa com 1 e no nosso exemplo é o ID
													//interrogação que esta na query
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				 contato.setIdcon(rs.getString(1));
				 contato.setNome(rs.getString(2));
				 contato.setFone(rs.getString(3));
				 contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	//editar o contato
	public void alterarContato(JavaBeans contato)
	{
		String update = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			//ATENÇÃO, siga a sequencia dos parametros, na query o primeiro parametro é o nome			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());			
			pst.executeUpdate(); //executa a query
			con.close(); //fecha a conexao
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD DELETE *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete  = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon()); //o objeto contato consegue acessar os metodos publicos da classe JavaBeans e obter o conteúdo da variavel idcon
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

