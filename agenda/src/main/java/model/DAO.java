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
	
	/**  M�dulo de conex�o *. */
	// Par�metros de conex�o
	private String driver = "com.mysql.cj.jdbc.Driver";// s� vai funcionar se tiver importado o drive pra pasta LIB
	// Ip do servidor, o nome do banco de dados e o fuso hor�rio de referencia
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
	// M�todo de conex�o
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

//	// teste de conex�o
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
			// abrir a conex�o com o banco
			Connection con = conectar();
			// Preparar a query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(sql_create); // P ma�usculo � uma classe modelo,
																		// p min�sculo � um m�todo
			// substituir os parametros (?) pelo conte�do das vari�veis JavaBeans, por isso
			// precisamos do PrepareStatement
			pst.setString(1, contato.getNome()); // 1 � o primeiro parametros
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar a query
			pst.executeUpdate();
			// Encerrar a conex�o com o banco
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
			// abrir a conex�o com o banco
			Connection con = conectar();
			// Preparar a query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(sql_read); // P ma�usculo � uma classe modelo,
																	// p min�sculo � um m�todo
			ResultSet rs = pst.executeQuery();
			// o la�o abaixo ser� executado enquanto houver contatos
			while (rs.next()) {
				// vari�veis de apoio que recebem os dados do banco
				// por quest�o didatica vou usar os mesmo nomes das vari�veis javaBeans
				String str_idcon = rs.getString(1); // recebe o primeiro campo/coluna
				String str_nome = rs.getString(2);
				String str_fone = rs.getString(3);
				String str_email = rs.getString(4);
				// populando o ArrayList
				// preciso criar outro objeto para enxergar as vari�veis JavaBeans, criado no
				// inicio deste m�todo
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
													// come�a com 1 e no nosso exemplo � o ID
													//interroga��o que esta na query
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
			//ATEN��O, siga a sequencia dos parametros, na query o primeiro parametro � o nome			
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
			pst.setString(1, contato.getIdcon()); //o objeto contato consegue acessar os metodos publicos da classe JavaBeans e obter o conte�do da variavel idcon
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

