package br.com.next.bean;

import java.util.Date;

public class Compras {
	
	private Date dataCompra;
	private Double valor;
	
	public Compras(Date dataCompra, Double valor) {
		super();
		this.dataCompra = dataCompra;
		this.valor = valor;
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
