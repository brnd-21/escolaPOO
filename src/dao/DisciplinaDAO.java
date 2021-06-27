package dao;

import java.sql.*;

import modelo.Disciplina;


public class DisciplinaDAO {
	
	Conexao conexao = new Conexao();
	
	public void insere(Disciplina disciplina) {

		try {
			// Cria um preparedStatement
			String sql = "insert into disciplinas (id_disc, nome, id_professor) values (?, ?, ?)";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			// Preenche os valores
			stmt.setInt(1, 0);
			stmt.setString(2, disciplina.getNome());
			stmt.setInt(3, disciplina.getProfessorResp());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public ResultSet consulta() {
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from disciplinas");
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void remove(int identificador) {
		
		try {
			String sql = "delete from disciplinas where `id_disc` = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			int indice = filtra(identificador);
			
			if(indice > 0) {
				
				stmt.setInt(1, identificador);
				stmt.execute();
				
				System.out.println("A Disciplina foi removida do banco de dados");
			}else if(identificador == 0)
				System.out.println("Operação cancelada");
			else
				System.err.println("O ID não existe na base de dados, tente novamente");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int filtra(int identificador) {
		
		PreparedStatement stmt;
		ResultSet rs = null;
		int indice = 0;

		try {
			
			String sql = "SELECT * FROM disciplinas where `id_disc` = ?";
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
			String sql = "delete from disciplinas where `id_professor` = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			stmt.setInt(1, prontuario);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}