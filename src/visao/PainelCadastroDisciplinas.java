package visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.CadastrarDisciplina;
import modelo.Disciplina;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PainelCadastroDisciplinas extends JPanel {
	private JTextField nome, profResp;

	ArrayList <Disciplina> listaDisciplinas = new ArrayList();
		
	public JTextField getNome() {
		return nome;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public JTextField getProfResp() {
		return profResp;
	}

	public void setProfResp(JTextField profResp) {
		this.profResp = profResp;
	}

	public PainelCadastroDisciplinas(PainelListaDisciplinas PainelListaDisciplinas) {
		setLayout(null);
		
		JLabel lblNome_disc = new JLabel("Nome: ");
		lblNome_disc.setBounds(28, 34, 70, 15);
		add(lblNome_disc);
		
		nome = new JTextField();
		nome.setBounds(116, 32, 114, 19);
		add(nome);
		nome.setColumns(10);
		
		JLabel lblProfResp = new JLabel("Professor Responsável: ");
		lblProfResp.setBounds(28, 70, 70, 15);
		add(lblProfResp);
		
		profResp = new JTextField();
		profResp.setBounds(116, 70, 114, 19);
		add(profResp);
		profResp.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina curso = new Disciplina(nome.getText(), Integer.parseInt(profResp.getText()));
				listaDisciplinas.add(curso);
			}
		});
		
		btnSalvar.setBounds(113, 154, 117, 25);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 CadastrarDisciplina controller = new CadastrarDisciplina();
		         controller.executa(PainelCadastroDisciplinas.this);
			}
		});
		
		add(btnSalvar);
	}
}