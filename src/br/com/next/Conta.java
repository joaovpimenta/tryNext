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

		Double valorSaque = util.readConsoleDouble("Digite o valor do saque: R$");
		while (this.saldo < valorSaque) {
			valorSaque = util.readConsoleDouble("Saldo insuficiente! Tente novamente: R$");
		}
		this.saldo -= valorSaque;

	}

	public void transferir() {

		Double valorTransferencia = util.readConsoleDouble("Qual valor deseja transferir? R$");
		if (this.saldo > valorTransferencia) {
			this.saldo -= valorTransferencia;
			util.writeConsole("Transferência Realizada com sucesso!\n");
		} else {
			util.writeConsole("O valor informado é superior ao seu saldo atual.\n");
		}
		util.writeConsole("Saldo atual: R$" + this.saldo + "\n");

	}

	public void depositar() {

		Double valorDeposito = util.readConsoleDouble("Qual valor deseja depositar? R$");
		this.saldo += valorDeposito;
		util.writeConsole("\nSaldo atual: R$" + this.saldo + "\n");
	}

	public void consultarSaldo() {
		
		util.writeConsole("Cliente: " + this.cliente.getNome() + "\nConta: " + this.numeroConta + "\nCPF: " + this.cliente.getCpf() + "\nSaldo Atual: " + this.saldo + "\n");
	}

}
