package br.com.next.bo;

import br.com.next.bean.CartaoCredito;
import br.com.next.bean.Cliente;
import br.com.next.utils.DataBase;

public class CartaoCreditoBO {

	CartaoCredito cartaoCredito;
	
	public CartaoCreditoBO(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
		
	}

	public CartaoCreditoBO(String senha, Cliente cliente) {
		this.cartaoCredito = novoCartaoCredito(senha, cliente);
		
	}
	
	private CartaoCredito novoCartaoCredito(String senha, Cliente cliente) {
		CartaoCredito cartaoCredito = new CartaoCredito(senha, cliente);
		//DataBase.
		return cartaoCredito;
	}

	public void adicionarCartaoCreditoBO(CartaoCredito cartaoCredito) {
		//this.cartaoCredito.g
		//this.conta.setCartaoCredito(cartaoCredito);
		//DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
	}
}
