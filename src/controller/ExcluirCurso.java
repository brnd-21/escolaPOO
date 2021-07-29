package controller;

import dao.CursoDAO;
import paineis.PainelExcluiCurso;

public class ExcluirCurso {

	public void executa(PainelExcluiCurso frame) {
		CursoDAO curdao = new CursoDAO();
		
		int identificador = Integer.parseInt(frame.getIdentificador().getText());
		
		curdao = new CursoDAO();
		curdao.remove(identificador);
	}
}