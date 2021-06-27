package dao;

import java.sql.*;

import modelo.Grupo;


public class GrupoDAO {
	
	public void insere(Grupo grupo) {
		Conexao conexao = new Conexao();
		try {
			// Cria um preparedStatement
			String sql = "insert into grupos (id_grupo, nome, tipo_grupo, qtd_alunos) values (?, ?, ?, ?)";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			// Preenche os valores
			stmt.setInt(1, 0);
			stmt.setString(2, grupo.getNome());
			stmt.setString(3, grupo.getTipoGrupo());
			stmt.setInt(4, grupo.getQtdGrupo());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public ResultSet consulta() {
	
		Conexao conexao = new Conexao();
		PreparedStatement stmt;
		ResultSet rs = null;
		
		try {
			stmt = conexao.getConn().prepareStatement("select * from grupos");
			rs = stmt.executeQuery();
			//stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void remove(int identificador) {
		
		Conexao conexao = new Conexao();
		
		try {
			String sql = "delete from grupos where id_grupo = ?";
			PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			
			int indice = filtra(identificador);
			
			if(indice > 0) {
				
				stmt.setInt(1, identificador);
				stmt.execute();
				stmt.close();
				System.out.println("O Grupo foi removido do banco de dados");
			}else if(identificador == 0)
				System.out.println("Operação cancelada");
			else
				System.err.println("O ID não existe na base de dados, tente novamente");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int filtra(int identificador) { // Responsável por validar se o Identificador existe na tabela
		Conexao conexao = new Conexao();
		PreparedStatement stmt;
		ResultSet rs = null;
		int indice = 0;

		try {
			
			String sql = "SELECT * FROM grupos where `id_grupo` = ?";
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
}