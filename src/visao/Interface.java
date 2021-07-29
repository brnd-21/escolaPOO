package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.SQLException;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import paineis.PainelCadastroCursos;
import paineis.PainelCadastroDisciplinas;
import paineis.PainelExcluiCurso;
import paineis.PainelExcluiDisciplina;
import paineis.PainelListaCursos;
import paineis.PainelListaDisciplinas;

public class Interface extends JFrame {
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Interface() throws SQLException {
		setTitle("Escola POO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// --------- Cursos --------- //
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		PainelListaCursos painelListaCursos = new PainelListaCursos();
		
		tabbedPane.addTab("Cadastro de Cursos",
		new PainelCadastroCursos(painelListaCursos));
		
		tabbedPane.addTab("Lista de Cursos", painelListaCursos);
		
		tabbedPane.addTab("Excluir Curso",
		new PainelExcluiCurso(painelListaCursos));
		
		// --------- Disciplinas --------- //
		
		PainelListaDisciplinas PainelListaDisciplinas = new PainelListaDisciplinas();
		
		tabbedPane.addTab("Cadastro de Disciplinas",
		new PainelCadastroDisciplinas(PainelListaDisciplinas));
		
		tabbedPane.addTab("Lista de Disciplinas", PainelListaDisciplinas);
		
		tabbedPane.addTab("Excluir Disciplina",
		new PainelExcluiDisciplina(PainelListaDisciplinas));
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
}