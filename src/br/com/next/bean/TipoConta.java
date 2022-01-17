package br.com.next.bean;

public enum TipoConta {

	CORRENTE(1), POUPANCA(2);

	private int id;

	TipoConta(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

}
