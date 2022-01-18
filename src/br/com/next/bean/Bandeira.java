package br.com.next.bean;

public enum Bandeira {
	
	VISA(1), MASTERCARD(2), ELO(3);
	
	int id;

	Bandeira(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

}
