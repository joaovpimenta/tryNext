package br.com.next.bean;

import java.util.UUID;

public class Conta {

	private String id;
	private Cliente cliente;
	private String numeroConta;
	private Double saldo;
	private Pix pix;
	private TipoConta tipoConta;



	public Conta() {

		this.setCliente(cliente);
		this.numeroConta = setNumeroConta();
		this.saldo = 0.00;

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
		return UUID.randomUUID().toString();
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Pix getPix() {
		return pix;
	}

	public void setPix(Pix pix) {
		this.pix = pix;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	

}
