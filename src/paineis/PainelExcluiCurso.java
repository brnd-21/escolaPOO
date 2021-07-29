package paineis;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ExcluirCurso;
import modelo.Curso;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PainelExcluiCurso extends JPanel{
	private JTextField identificador;

	ArrayList <Curso> listaCursos = new ArrayList();
		
	public JTextField getIdentificador() {
		return identificador;
	}

	public void setIdentificador(JTextField identificador) {
		this.identificador = identificador;
	}

	public PainelExcluiCurso(PainelListaCursos PainelListaCursos) {
		setLayout(null);
		
		JLabel lblIdentificador = new JLabel("Identificador: ");
		lblIdentificador.setBounds(28, 34, 70, 15);
		add(lblIdentificador);
		
		identificador = new JTextField();
		identificador.setBounds(116, 32, 114, 19);
		add(identificador);
		identificador.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(113, 154, 117, 25);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ExcluirCurso controller = new ExcluirCurso();
		         controller.executa(PainelExcluiCurso.this);
			}
		});
		
		add(btnExcluir);
	}
}
