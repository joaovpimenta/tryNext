package br.com.next;

import br.com.util.Util;

public class Conta {

	Util util = new Util(); // Ainda precisa mover entradas para o menu

	protected Cliente cliente;
	protected String numeroConta;
	protected Double saldo;
	protected String senha;

	static Integer totalConta = 0;

	public Conta(Cliente cliente, String senha) {
		this.cliente = cliente;
		this.numeroConta = numeroConta();
		this.saldo = 0.00;
		this.senha = senha;

	}

	private String numeroConta() {
		return String.valueOf(totalConta++);
	}

	public void saque() {

		Double valorSaque = util.readConsoleDouble("Digite o valor do saque: R$");
		while (this.saldo < valorSaque) {
			valorSaque = util.readConsoleDouble("Saldo insuficiente! Tente novamente: R$");
		}
		this.saldo -= valorSaque;
		atualizaTipo();
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
		atualizaTipo();

	}

	public void depositar() {

		Double valorDeposito = util.readConsoleDouble("Qual valor deseja depositar? R$");
		this.saldo += valorDeposito;
		util.writeConsole("\nSaldo atual: R$" + this.saldo + "\n");
		atualizaTipo();
	}

	public void consultarSaldo() {

		util.writeConsole("Cliente: " + this.cliente.getNome() + "\nConta: " + this.numeroConta + "\nCPF: "
				+ this.cliente.getCpf() + "\nSaldo Atual: R$" + this.saldo + "\n");
	}

	public void atualizaTipo() {
		if (this.saldo < 5000.00) {
			this.cliente.setTipo(TipoCliente.COMUM);
		} else {
			if (this.saldo >= 5000.00 && this.saldo < 15000.00) {
				this.cliente.setTipo(TipoCliente.SUPER);
			} else {
				this.cliente.setTipo(TipoCliente.PREMIUM);
			}
		}

	}

	// Getters e Setters

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
