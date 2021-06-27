package dao;

import java.sql.*;

import modelo.Relacao;

public class RelacaoDAO {
	
	Conexao conexao = new Conexao();
	
	public void insere(Relacao relacao) {

		try {
			// Cria um preparedStatement
			String sql = "insert into relacoes (id_relacao, id_user1, id_user2, tipo_relacao) values (?, ?, ?, ?)";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			// Preenche os valores
			stmt.setInt(1, 0);
			stmt.setInt(2, relacao.getPessoa1());
			stmt.setInt(3, relacao.getPessoa2());
			stmt.setString(4, relacao.getStatus());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public ResultSet consulta() {
	
		PreparedStatement stmt;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from relacoes");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void remove(int identificador) {
				
		try {
			String sql = "delete from relacoes where `id_relacao` = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			int indice = filtra(identificador);
			
			if(indice > 0) {
				stmt.setInt(1, identificador);
				stmt.execute();
				
				System.out.println("A Relação foi removida do banco de dados");
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
			
			String sql = "SELECT * FROM relacoes where `id_relacao` = ?";
			Connection conn = conexao.getConn();
			stmt = conn.prepareStatement(sql);
			
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
			String sql = "delete from relacoes where `id_user1` = ? or `id_user2` = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			stmt.setInt(1, prontuario);
			stmt.setInt(2, prontuario);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet consultaFiltrada() {
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select id_user1, count(id_user1) as 'Relacoes' from relacoes group by id_user1");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}