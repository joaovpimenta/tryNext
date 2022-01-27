package br.com.next.bo;

import java.util.Date;

import br.com.next.bean.CartaoCredito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Compras;
import br.com.next.utils.DataBase;

public class CartaoCreditoBO {

	CartaoCredito cartaoCredito;

	public CartaoCreditoBO(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;

	}

	public CartaoCreditoBO(Cliente cliente, String senha, Integer diaVencimento) {
		this.cartaoCredito = new CartaoCredito(cliente, senha, diaVencimento);
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public boolean novaCompra(Date dataCompra, Double valor, String descricao, String nomeProduto, ContaBO contaEmUso) {
		Double limiteCartao = cartaoCredito.getLimite();
		
		if (limiteCartao < valor) {
			return false;
		}
		
		cartaoCredito.setLimite(limiteCartao-valor);
		Compras compras = new Compras(dataCompra, valor, descricao, nomeProduto);
		return DataBase.novaCompra(compras, contaEmUso.getConta());
	}
}
