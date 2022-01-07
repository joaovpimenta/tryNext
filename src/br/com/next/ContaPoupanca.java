package br.com.next;

public class ContaPoupanca extends Conta {

	Double taxaRendimento = 0.03;

	public ContaPoupanca(Cliente cliente, String senha) {
		super(cliente, senha);
	}

	@SuppressWarnings("unused")
	private void acrescentarRendimento() {
		super.setSaldo(saldo+(saldo*taxaRendimento));

	}
	
	@Override
	public void transferir() {
		// TODO Auto-generated method stub
		super.transferir();
	}

}
