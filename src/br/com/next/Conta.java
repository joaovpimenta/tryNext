package br.com.next;

public class Conta {
	
	private String numeroConta;
	private Double saldo;
	private Cliente cliente;
	static Integer totalConta = 0;

	private String numeroConta() {
		return String.valueOf(totalConta++);
	}
	private void cadastrarConta(Cliente cliente) {
		this.cliente = cliente;
		this.numeroConta = numeroConta();
		this.saldo = 0.00;
	}
	public void transferir() {
	}
	public void depositar() {
	}
	public void consultarSaldo() {
	}
	
}
