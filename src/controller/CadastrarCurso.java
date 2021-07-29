package controller;

import dao.CursoDAO;
import modelo.Curso;
import paineis.PainelCadastroCursos;

public class CadastrarCurso {
	
	public void executa(PainelCadastroCursos frame) {
		CursoDAO curdao = new CursoDAO();
		
		Curso curso = new Curso(frame.getNome().getText());
		
		curdao = new CursoDAO();
		curdao.insere(curso);
	}
}