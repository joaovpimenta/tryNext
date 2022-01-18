package br.com.next.bean;

import java.util.Date;
import java.util.UUID;

public class Conta {

	private String id;
	private Cliente cliente;
	private String numeroConta;
	private Double saldo;
	private Pix pix;
	private TipoConta tipoConta;
	private Date dataExecucao;
	private CartaoCredito cartaoCredito;
	private CartaoDebito cartaoDebito;

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

	/**
	 * @return Retorna o valor do atributo data de Execução de Rendimentos ou Taxas
	 *         em Conta
	 */
	public Date getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataCriacao Insere valor no atributo data de Execução de Rendimentos
	 *                    ou Taxas em Conta
	 */
	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return the cartaoCredito
	 */
	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	/**
	 * @param cartaoCredito the cartaoCredito to set
	 */
	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	/**
	 * @return the cartaoDebito
	 */
	public CartaoDebito getCartaoDebito() {
		return cartaoDebito;
	}

	/**
	 * @param cartaoDebito the cartaoDebito to set
	 */
	public void setCartaoDebito(CartaoDebito cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}

}
