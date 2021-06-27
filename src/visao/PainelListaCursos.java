package visao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CursoDAO;

public class PainelListaCursos extends JPanel {
	private JTable table;
	int id_curso;
	String nome;
	
	CursoDAO curdao = new CursoDAO();
	
	public PainelListaCursos() throws SQLException {
		setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 37, 282, 170);
		add(scrollPane_1);
		
		table = new JTable(2, 6);
		
		// As colunas da tabela
		String colunas[] = {"Nome"};
		
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		
		ResultSet resultado = curdao.consulta();
		
		while (resultado.next()) {
			// id_curso = resultado.getInt("id_curso");
			String nome = resultado.getString("nome");
			
			// id_curso = id_curso.toString();
			
			modelo.addRow(new String[]{nome});
		}
		
		table.setModel(modelo);	
		scrollPane_1.setViewportView(table);
	}
}