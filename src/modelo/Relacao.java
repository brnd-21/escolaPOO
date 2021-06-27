package modelo;

public class Relacao {

	private int pessoa1, pessoa2;
	private String status;
	
	public int getPessoa1() {
		return pessoa1;
	}

	public void setPessoa1(int pessoa1) {
		this.pessoa1 = pessoa1;
	}

	public int getPessoa2() {
		return pessoa2;
	}

	public void setPessoa2(int pessoa2) {
		this.pessoa2 = pessoa2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}