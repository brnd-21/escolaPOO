package modelo;

public class Usuario {

	private String nome, email, area;
	private int prontuario;
	
	public String getNome() {
		return nome;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getProntuario() {
		return prontuario;
	}
	
	public void setProntuario(int prontuario) {
		this.prontuario = prontuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}