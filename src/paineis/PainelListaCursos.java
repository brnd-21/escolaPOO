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

import dao.CursoDAO;

public class PainelListaCursos extends JPanel {
	private JTable table;

	int id_curso;
	String nome;
	
	CursoDAO curdao = new CursoDAO();
	
	public PainelListaCursos() throws SQLException {
		setLayout(null);
		JPanel gui = new JPanel(new BorderLayout(2, 3));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 37, 282, 170);
		add(scrollPane_1);
		
		table = new JTable(2, 6);
		
		// As colunas da tabela
		String colunas[] = { "Identificador", "Nome" };
		
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		
		ResultSet resultado = curdao.consulta();
		
		while (resultado.next()) {
			id_curso = resultado.getInt("id_curso");
			String nome = resultado.getString("nome");
			
			String id_curso_cr = Integer.toString(id_curso);
			
			modelo.addRow(new String[]{id_curso_cr, nome});
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
				
	            ResultSet resultado = curdao.consulta();
	            
				try {
					while (resultado.next()) {
						id_curso = resultado.getInt("id_curso");
						String nome = resultado.getString("nome");
						
						String id_curso_cr = Integer.toString(id_curso);
						
						modelo.addRow(new String[]{id_curso_cr, nome});
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