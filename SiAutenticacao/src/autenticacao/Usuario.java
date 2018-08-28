package autenticacao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Usuario {
	
	private String login;
	private String senha;
	private int autorizacao;
	
	private Scanner teclado;
	
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}	
	
	public Usuario() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void criarConta() {
		
		teclado = new Scanner(System.in);
		
		System.out.println("Digite seu nome de login:");
		this.setLogin(teclado.next());
		
		 System.out.println("Digite a sua senha:");
		 this.setSenha(teclado.next());
		 
		System.out.println("");
		 
		this.inserirLogin();			
				 
	}
	
	public String criptografar(String senha){
		 /* try{
		   MessageDigest digest = MessageDigest.getInstance("MD5");
		                 digest.update(senha.getBytes());
		   BASE64Encoder encoder = new BASE64Encoder();
		          return encoder.encode(digest.digest());
		  }catch(NoSuchAlgorithmException ns){
		   ns.printStackTrace();
		  }*/
		  return senha;
		 }	
	
	public int inserirLogin() {

		Connection conn = BancoSi.getConnection();
		Statement stmt = BancoSi.getStatement(conn);
		int resultado = 0;
		String query = "INSERT INTO tb_usuario (login, senha) VALUES ('" + this.getLogin() + "','" + criptografar(this.getSenha()) +"')";
		try {
			resultado = stmt.executeUpdate(query);
			System.out.println("Usuário cadastrado com sucesso!");
			System.out.println("__________________________________");
		}catch (SQLException e) {
			System.out.println("Erro ao executar Query de Cadastro de Colecionador!");
		}finally {
			BancoSi.closeStatement(stmt);
			BancoSi.closeConnection(conn);
		}
		return resultado;
		
	}

	public void autenticarConta() {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Digite seu nome de login:");
		 this.setLogin(teclado.next());
		
		 System.out.println("Digite a sua senha:");
		 this.setSenha(teclado.next());
		 
		System.out.println("");
		 
		this.autenticarUsuario();
		
		}
		
	
	public boolean autenticarUsuario() {
		
		Connection conn = BancoSi.getConnection();
		Statement stmt = BancoSi.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT login, senha FROM tb_usuario WHERE login= '" + this.getLogin() + "' and senha= '" + criptografar(this.getSenha()) +"'";
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				System.out.println("Bem vindo, usuário autenticado com sucesso!");
				return true;
			}else {
				System.out.println("Credenciais inválidas! Tente novamente.");
				System.out.println("__________________________________");
			}
			
		}catch (SQLException e) {
			System.out.println("Erro ao executar Query que autentica os usuários!");
			return false;
		}finally {
			BancoSi.closeResultSet(resultado);
			BancoSi.closeStatement(stmt);
			BancoSi.closeConnection(conn);
		}
		return false;
		
	}

	public int getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(int autorizacao) {
		this.autorizacao = autorizacao;
	}
	

}
