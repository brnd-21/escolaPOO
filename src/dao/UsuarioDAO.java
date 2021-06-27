package dao;

import java.sql.*;

import modelo.Usuario;

public class UsuarioDAO {
	
	static Conexao conexao = new Conexao();
	
	public void insere(Usuario usuario) {
		
		PreparedStatement stmt = null;
		
		try {
			// Cria um preparedStatement
			String sql = "insert into usuarios (nome, prontuario, email, area) values (?, ?, ?, ?)";
			stmt = conexao.getConn().prepareStatement(sql);
			
			// Preenche os valores
			stmt.setString(1, usuario.getNome());
			stmt.setInt(2, usuario.getProntuario());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getArea());
			stmt.execute();
			
		} catch (SQLException e) {
			System.err.println("N�o foi possivel adicionar um novo usu�rio ao banco");
		}
	}
		
	public static ResultSet consulta(){
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from usuarios order by `prontuario`");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			System.err.println("N�o foi poss�vel coletar os dados de usu�rios do banco");
		}
				
		return rs;
	}
	
	public static ResultSet contagem() throws SQLException{
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select count(prontuario) as 'pront' from usuarios");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			System.err.println("N�o foi poss�vel coletar os dados de usu�rios do banco");
		}
		
		return rs;
	}

	public void remove(int identificador) {
		
		PreparedStatement stmt = null;
		
		try {
			String sql = "delete from usuarios where `prontuario` = ?";
			stmt = conexao.getConn().prepareStatement(sql);
			
			int indice = filtra(identificador);
			
			if(indice > 0) {
				
				stmt.setInt(1, identificador);
				stmt.execute();
				
				System.out.println("O Usu�rio foi removido do banco de dados");
			}else if(identificador == 0)
				System.out.println("Opera��o cancelada");
			else
				System.err.println("O Prontu�rio n�o existe na base de dados, tente novamente");
			
		} catch (SQLException e) {
			System.err.println("N�o foi possivel remover o usu�rio do banco");
		}
	}
	
	public static int filtra(int identificador) { // Respons�vel por validar se o prontu�rio existe na tabela
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int indice = 0;
		
		try {
			String sql = "SELECT * FROM usuarios where `prontuario` = ?";
			Connection conn = conexao.getConn();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, identificador);
			stmt.execute();
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				indice++;
			}	
		} catch (SQLException e) {
			System.err.println("N�o foi poss�vel filtrar o usu�rio no banco");
		}
		
		return indice;
	}
	
	public static ResultSet consultaProfessor() {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from usuarios where `area` is not null");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			System.err.println("N�o foi possivel consultar algum professor no banco");
		}
		
		return rs;
	}
	
	public static ResultSet consultaFiltrada(int prontuario) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from usuarios where `prontuario` like ?");
			stmt.setInt(1, prontuario);
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			System.err.println("N�o foi poss�vel coletar os dados de um usu�rio no banco");
		}
		
		return rs;
	}
	
	public static ResultSet consultaGrupos(int pessoa_1) {
				
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from usuarios where `prontuario` != ?");
			stmt.setInt(1, pessoa_1);
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			System.err.println("N�o foi possivel consultar a lista de usu�rios no banco");
		}
		
		return rs;
	}
}