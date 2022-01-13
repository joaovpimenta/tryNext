package br.com.next.bean;

import br.com.next.utils.Util;

public class Conta {

	Util util = new Util(); // Ainda precisa mover entradas para o menu

	protected String id;
	protected Cliente cliente;
	protected String numeroConta;
	protected Double saldo;

	static Integer totalConta = 0;
	static Integer totalId = 0;

	public Conta() {
		this.id = numeroId();
		this.setCliente(cliente);
		this.numeroConta = numeroConta();
		this.saldo = 0.00;

	}

	private String numeroId() {
		return String.valueOf(totalId++);
	}

	private String numeroConta() {
		return String.valueOf(totalConta++);
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

	public String setNumeroConta() {
		return String.valueOf(totalConta++);
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
