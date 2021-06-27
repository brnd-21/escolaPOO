package visao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DisciplinaDAO;

public class PainelListaDisciplinas extends JPanel {
	private JTable table;
	int id_disciplina_bc, id_professor_resp;
	String nome;
	
	DisciplinaDAO disdao = new DisciplinaDAO();
	
	public PainelListaDisciplinas() throws SQLException {
		setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 37, 282, 170);
		add(scrollPane_1);
		
		table = new JTable(3, 6);
		
		// As colunas da tabela
		String colunas[] = {"ID Disciplina", "Nome", "Prontuário Professor"};
		
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		
		ResultSet resultado = disdao.consulta();
		
		while (resultado.next()) {
			id_disciplina_bc = resultado.getInt("id_disc");
			nome = resultado.getString("nome");
			id_professor_resp = resultado.getInt("id_professor");
			
			String id_disciplina = Integer.toString(id_disciplina_bc);
			String id_professor = Integer.toString(id_professor_resp);
			
			modelo.addRow(new String[]{id_disciplina, nome, id_professor});
		}
		
		table.setModel(modelo);	
		scrollPane_1.setViewportView(table);
	}
}