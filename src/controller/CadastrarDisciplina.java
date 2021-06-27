package controller;

import dao.DisciplinaDAO;
import modelo.Disciplina;
import visao.PainelCadastroDisciplinas;

public class CadastrarDisciplina {
	
	public void executa(PainelCadastroDisciplinas frame) {
		DisciplinaDAO disdao = new DisciplinaDAO();
		
		int prof_resp = Integer.parseInt(frame.getProfResp().getText()); // Convertendo a String para inteiro
		Disciplina disciplina = new Disciplina(frame.getNome().getText(), prof_resp);
		
		disdao = new DisciplinaDAO();
		disdao.insere(disciplina);
		
		System.out.println("Valor salvo no banco, reinicie para ver as novidades");
	}
}