package visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.CadastrarCurso;
import modelo.Curso;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PainelCadastroCursos extends JPanel {
	private JTextField nome;

	ArrayList <Curso> listaCursos = new ArrayList();
		
	public JTextField getNome() {
		return nome;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public PainelCadastroCursos(PainelListaCursos PainelListaCursos) {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(28, 34, 70, 15);
		add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(116, 32, 114, 19);
		add(nome);
		nome.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Curso curso = new Curso(nome.getText());
				listaCursos.add(curso);
			}
		});
		
		btnSalvar.setBounds(113, 154, 117, 25);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 CadastrarCurso controller = new CadastrarCurso();
		         controller.executa(PainelCadastroCursos.this);
			}
		});
		
		add(btnSalvar);
	}
}