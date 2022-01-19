package br.com.next.bean;

public class CartaoDebito extends Cartao {

	private Double limiteTransacao;

	public CartaoDebito(String senha, Double limiteTransacao, Cliente cliente) {
		super(senha, cliente);
		this.limiteTransacao = limiteTransacao;
	}

	public Double getLimiteTransacao() {
		return limiteTransacao;
	}

	public void setLimiteTransacao(Double limiteTransacao) {
		this.limiteTransacao = limiteTransacao;
	}

}
