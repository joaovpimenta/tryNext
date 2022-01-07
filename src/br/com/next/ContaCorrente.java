package br.com.next;

public class ContaCorrente extends Conta {

	Double taxaManutencao = 0.45;

	public ContaCorrente(Cliente cliente, String senha) {
		super(cliente, senha);
	}

	private void descontarTaxa() {
		super.setSaldo(saldo - (saldo * taxaManutencao));

	}

	@Override
	public void transferir() {
		// TODO Auto-generated method stub
		super.transferir();
	}

}
