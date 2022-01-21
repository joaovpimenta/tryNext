package br.com.next.bean;

import java.util.Date;

import br.com.next.utils.Util;

public class Conta {

	private String id;
	private Cliente cliente;
	private Integer numeroConta;
	private Double saldo;
	private Pix pix;
	private TipoConta tipoConta;
	private Date dataExecucao;
	private CartaoCredito cartaoCredito;
	private CartaoDebito cartaoDebito;

	
	
	// Getters e Setters

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta() {
		this.numeroConta = Util.randInt(10000, 99999);
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

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public CartaoDebito getCartaoDebito() {
		return cartaoDebito;
	}

	public void setCartaoDebito(CartaoDebito cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}

}
