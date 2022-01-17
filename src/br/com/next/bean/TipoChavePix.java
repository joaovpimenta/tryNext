package br.com.next.bean;

public enum TipoChavePix {
	CPF(1), EMAIL(2), TELEFONE(3), ALEATORIO(4);

	private int id;

	private TipoChavePix(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

}
