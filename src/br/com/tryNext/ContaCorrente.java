package br.com.tryNext;

import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;

public class ContaCorrente extends Conta {

	Double taxaManutencao = 0.45;

	public ContaCorrente(Cliente cliente, String senha) {
		super(cliente, senha);
	}

	@SuppressWarnings("unused")
	private void descontarTaxa() {
		super.setSaldo(saldo - (saldo * taxaManutencao));

	}

	@Override
	public void transferir() {
		// TODO Auto-generated method stub
		super.transferir();
	}

}