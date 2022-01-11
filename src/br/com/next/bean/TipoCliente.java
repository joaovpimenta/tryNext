package br.com.next.bean;

public enum TipoCliente {
	COMUM(1), SUPER(2), PREMIUM(3);

	private int id;

	TipoCliente(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}
