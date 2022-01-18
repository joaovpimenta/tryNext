package br.com.next.bean;

import java.util.List;

public class CartaoDebito extends Cartao{

	Double limiteTransacao;
	
	public CartaoDebito(String numeroCartao, Bandeira bandeira, String senha, Boolean isAtivo,
			List<Compras> compras, Double limiteTransacao) {
		super(numeroCartao, bandeira, senha, isAtivo, compras);
		this.limiteTransacao = limiteTransacao;
	}

	public Double getLimiteTransacao() {
		return limiteTransacao;
	}

	public void setLimiteTransacao(Double limiteTransacao) {
		this.limiteTransacao = limiteTransacao;
	}

}
