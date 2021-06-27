package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Paineis extends JFrame {
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paineis frame = new Paineis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Paineis() throws SQLException {
		setTitle("Cadastro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		PainelListaCursos painelListaCursos = new PainelListaCursos();
		tabbedPane.addTab("Cadastro de Cursos",
		new PainelCadastroCursos(painelListaCursos));
		tabbedPane.addTab("Lista de Cursos", painelListaCursos);
		
		PainelListaDisciplinas PainelListaDisciplinas = new PainelListaDisciplinas();
		tabbedPane.addTab("Cadastro de Disciplinas",
		new PainelCadastroDisciplinas(PainelListaDisciplinas));
		tabbedPane.addTab("Lista de Disciplinas", PainelListaDisciplinas);
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}
}