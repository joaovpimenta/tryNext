package br.com.next.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class Cartao {

	String numeroCartao;
	Bandeira bandeira;
	String senha;
	Boolean isAtivo;
	List<Compras> compras;

	public Cartao(String numeroCartao, Bandeira bandeira, String senha, Boolean isAtivo, List<Compras> compras) {
		super();
		this.numeroCartao = numeroCartao;
		this.bandeira = bandeira;
		this.senha = senha;
		this.isAtivo = isAtivo;
		this.compras = new ArrayList<Compras>();
		
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Bandeira getBandeira() {
		return bandeira;
	}
	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
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
	public List<Compras> getCompras() {
		return compras;
	}
	public void setCompras(List<Compras> compras) {
		this.compras = compras;
	}
	
}
