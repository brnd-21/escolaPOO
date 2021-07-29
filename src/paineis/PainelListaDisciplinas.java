package paineis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
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
		JPanel gui = new JPanel(new BorderLayout(2, 3));
		
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
		
		JButton btnAtualizar = new JButton("Atualizar lista");
		
		btnAtualizar.setBounds(0, 0, 117, 25);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	            int rc = modelo.getRowCount();
	            for(int i = 0; i < rc; i++){
	                modelo.removeRow(0);
	            }
				
	            ResultSet resultado = disdao.consulta();
	            
				try {
					while (resultado.next()) {
						id_disciplina_bc = resultado.getInt("id_disc");
						nome = resultado.getString("nome");
						id_professor_resp = resultado.getInt("id_professor");
						
						String id_disciplina = Integer.toString(id_disciplina_bc);
						String id_professor = Integer.toString(id_professor_resp);
						
						modelo.addRow(new String[]{id_disciplina, nome, id_professor});
						
						table.setModel(modelo);	
						scrollPane_1.setViewportView(table);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
		        gui.revalidate();
		        gui.repaint();
			}
		});
		
		add(btnAtualizar);
	}
}