package dao;

import java.sql.*;

public class Conexao {
	Connection conn = null;
	
	public Conexao() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escolaPOO", "root", "");
		} catch (SQLException e) {
			System.err.println("Não foi possivel fazer a conexão com o banco no momento");
		}
	}
	
	public Connection getConn() {		
		return conn;
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}