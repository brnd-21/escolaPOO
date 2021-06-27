package dao;

import java.sql.*;

import modelo.Conteudo;

public class ConteudoDAO {
	
	Conexao conexao = new Conexao();
	
	public void insere(Conteudo conteudo) {
		
		try {
			// Cria um preparedStatement
			String sql = "insert into conteudos (id_cont, titulo, conteudo, id_responsavel) values (?, ?, ?, ?)";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			// Preenche os valores
			stmt.setInt(1, 0);
			stmt.setString(2, conteudo.getNome());
			stmt.setString(3, conteudo.getConteudo());
			stmt.setInt(4, conteudo.getResponsavel());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public ResultSet consulta() {
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from conteudos");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void remove(int identificador) {
			
		try {
			String sql = "delete from conteudos where id_cont = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			int indice = filtra(identificador);
			
			if(indice > 0) {
				
				stmt.setInt(1, identificador);
				stmt.execute();
				
				System.out.println("O Conteúdo foi removido do banco de dados");
			}else if(identificador == 0)
				System.out.println("Operação cancelada");
			else
				System.err.println("O ID não existe na base de dados, tente novamente");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int filtra(int identificador) { // Responsável por validar se o Identificador existe na tabela
		
		PreparedStatement stmt;
		ResultSet rs = null;
		int indice = 0;

		try {
			
			String sql = "SELECT * FROM conteudos where `id_cont` = ?";
			stmt = conexao.getConn().prepareStatement(sql);
			
			stmt.setInt(1, identificador);
			stmt.execute();
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				indice += 1;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return indice;
	}
	
public void exclusaoCadeiada(int prontuario) {
		
		try {
			String sql = "delete from conteudos where `id_responsavel` = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			stmt.setInt(1, prontuario);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}