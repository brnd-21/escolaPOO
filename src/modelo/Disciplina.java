package modelo;

public class Disciplina {

	private String nome;
	private int professorResp;
	
	public Disciplina(String nome, int professorResp) {
		this.nome = nome;
		this.professorResp = professorResp;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getProfessorResp() {
		return professorResp;
	}
	
	public void setProfessorResp(int professorResp) {
		this.professorResp = professorResp;
	}	
}