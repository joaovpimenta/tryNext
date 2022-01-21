package br.com.next.bean;

import java.util.Date;

public class Compras {
	
	private Date dataCompra;
	private Double valor;
	private String descricao;
	private String nomeProduto;
	
	public Compras(Date dataCompra, Double valor, String descricao, String nomeProduto) {
		super();
		this.dataCompra = dataCompra;
		this.valor = valor;
		this.descricao = descricao;
		this.nomeProduto = nomeProduto;
	}
	
	//GETTERS E SETTERS
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

}
