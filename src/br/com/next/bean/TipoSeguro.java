package br.com.next.bean;

public enum TipoSeguro {
	
	MORTE("Morte"), INVALIDEZ("Invalidez"), DESEMPREGO("Desemprego");

	private String nome;
	
	TipoSeguro(String nome) {
		this.nome = nome;
	}
	
	public String getSeguro() {
		return nome;
	}

}
