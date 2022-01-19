package br.com.next.bo;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;

public class CartaoDebitoBO {

	CartaoDebito cartaoDebito;
	
	public CartaoDebitoBO(CartaoDebito cartaoDebito) {
		this.cartaoDebito = cartaoDebito;
		
	}

	public CartaoDebitoBO(String senha, Double limiteTransacao, Cliente cliente) {
		this.cartaoDebito = novoCartaoDebito(senha, limiteTransacao, cliente);
		
	}
	
	private CartaoDebito novoCartaoDebito(String senha, Double limiteTransacao, Cliente cliente) {
		CartaoDebito cartaoDebito = new CartaoDebito(senha, limiteTransacao, cliente);
		return cartaoDebito;
	}

}
