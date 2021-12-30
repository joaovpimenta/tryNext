package br.com.next;

import br.com.util.Util;

public class Conta {

	Util util = new Util();

	private String numeroConta;
	private Double saldo;
	private Cliente cliente;
	static Integer totalConta = 0;

	private String numeroConta() {
		return String.valueOf(totalConta++);
	}

	public void cadastrarConta(Cliente cliente) { // precisa ser private
		this.cliente = cliente;
		this.numeroConta = numeroConta();
		this.saldo = 0.00;
		String senhaCadastrada;
		do {
			senhaCadastrada = util.readConsole("Cadastre uma senha (apenas 4 digitos): ");
		} while (!senhaCadastrada.matches("[0-9]{4}"));

	}

	public void saque() {

		Double valor = util.readConsoleDouble("Digite o valor do saque: R$");
		while (this.saldo < valor) {
			valor = util.readConsoleDouble("Saldo insuficiente! Tente novamente: R$");
		}

	}

	public void transferir() {
	}

	public void depositar() {
	}

	public void consultarSaldo() {
	}

}
