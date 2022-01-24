package br.com.next.bo;
import java.util.Date;

import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Compras;
import br.com.next.utils.DataBase;

public class CartaoDebitoBO {

	CartaoDebito cartaoDebito;
	
	public CartaoDebitoBO(CartaoDebito cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
		
	}

	public CartaoDebitoBO(Cliente cliente, String senha, Double limiteTransacao) {
		this.cartaoDebito = novoCartaoDebito(cliente, senha, limiteTransacao);
		
	}
	
	private CartaoDebito novoCartaoDebito(Cliente cliente, String senha, Double limiteTransacao) {
		CartaoDebito cartaoDebito = new CartaoDebito(senha, limiteTransacao, cliente);
		return cartaoDebito;
	}

	public CartaoDebito getCartaoDebito() {
		return cartaoDebito;
	}

	public void setCartaoDebito(CartaoDebito cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
	}

	public boolean novaCompra(Date dataCompra, Double valor, String descricao, String nomeProduto, ContaBO contaMovimentada) {
		if (valor > cartaoDebito.getLimiteTransacao()) {
			return false;
		}
		Compras compras = new Compras(dataCompra, valor, descricao, nomeProduto);
		return DataBase.novaCompra(compras, contaMovimentada.getConta());
	}
	
	public Double getLimite() {
		return cartaoDebito.getLimiteTransacao();
	}

	public void setLimite(Double limiteTransacao) {
		this.cartaoDebito.setLimiteTransacao(limiteTransacao);
	}
	
}
