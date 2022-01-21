package br.com.next.bo;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;

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

}
