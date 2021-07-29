package controller;

import dao.DisciplinaDAO;
import paineis.PainelExcluiDisciplina;

public class ExcluirDisciplina {

	public void executa(PainelExcluiDisciplina frame) {
		DisciplinaDAO disdao = new DisciplinaDAO();
		
		int identificador = Integer.parseInt(frame.getIdentificador().getText());
		
		disdao = new DisciplinaDAO();
		disdao.remove(identificador);
	}
}