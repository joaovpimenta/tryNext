package br.com.next.bean;

import java.util.ArrayList;
import java.util.List;

import br.com.next.utils.Util;

public abstract class Cartao {

	String numeroCartao;
	Bandeira bandeira;
	String senha;
	Boolean isAtivo;
	List<Compras> compras;

	public Cartao(String senha, Cliente cliente) {
		super();
		this.setNumeroCartao();
		this.setBandeira(cliente);
		this.senha = senha;
		this.isAtivo = true;
		this.compras = new ArrayList<Compras>();
		
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao() {
		String numeroCartao = Util.randInt(1000, 9999)+" "+Util.randInt(1000, 9999)+" "+Util.randInt(1000, 9999)+" "+Util.randInt(1000, 9999);
		this.numeroCartao = numeroCartao;
	}
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Cliente cliente) {
		TipoCliente tipoCliente = cliente.getTipo();
		Bandeira bandeira = (tipoCliente == TipoCliente.COMUM) ? Bandeira.ELO : (tipoCliente == TipoCliente.SUPER) ? Bandeira.MASTERCARD : Bandeira.VISA;
		this.bandeira = bandeira;

		;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getIsAtivo() {
		return isAtivo;
	}
	public void setIsAtivo() {
		this.isAtivo = (this.getIsAtivo()) ? false : true;
	}
	public String gerarFatura() {
		String fatura = "";
		for (int i = 0; i < compras.size(); i++) {
			fatura += compras.get(i).getNomeProduto() + compras.get(i).getDescricao() + "R$" + compras.get(i).getValor() + compras.get(i).getDataCompra();
		}
		return fatura;
	}
	public List<Compras> getCompras() {
		return compras;
	}
	public void setCompras(List<Compras> compras) {
		this.compras = compras;
		
	}
	
}
