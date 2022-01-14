package br.com.next.bo;

import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.TipoCliente;
import br.com.next.utils.DataBase;

public class ContaBO {

	private Conta conta;

	public ContaBO(Conta conta) {
		this.conta = conta;
	}

	public ContaBO(Cliente cliente) {
		this.conta = this.novaConta(cliente);

	}

	private Conta novaConta(Cliente cliente) {
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setNumeroConta();
		conta.setSaldo(0.0);

		DataBase.setContaDB(conta.getNumeroConta(), conta);
		System.out.println("O n�mero da sua conta �: " + conta.getNumeroConta());

		return conta;
	}

	public boolean transferir(Conta contaDestino, double valor) {

		if (this.conta.getSaldo() >= valor) {
			double saldo = this.conta.getSaldo();
			saldo -= valor;
			this.conta.setSaldo(saldo);

			double saldoDestino = contaDestino.getSaldo();
			saldoDestino += valor;
			contaDestino.setSaldo(saldoDestino);

			System.out.println("Saldo atual: R$" + this.conta.getSaldo() + "\n");
			this.atualizaTipo();

			DataBase.setContaDB(contaDestino.getNumeroConta(), contaDestino);
			DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);

			return true;

		} else {
			System.out.println("O valor informado � superior ao seu saldo atual.\n");
			return false;
		}
	}

	/**
	 * Esse m�todo deposita um valor na conta informada
	 * 
	 * @param contaDestino    Em qual conta ser� depositado
	 * @param valorDepositado Quanto ser� depositado na conta
	 */
	public void depositar(Conta contaDestino, Double valorDepositado) {

		Double saldoDestino = contaDestino.getSaldo();
		saldoDestino += valorDepositado;
		contaDestino.setSaldo(saldoDestino);
		DataBase.setContaDB(contaDestino.getNumeroConta(), contaDestino);

		System.out.println("\nSaldo atual: R$" + this.conta.getSaldo() + "\n");
		this.atualizaTipo();
	}

	public void consultaSaldo() {
		System.out.println("Cliente: " + this.conta.getCliente().getNome() + "\nConta: " + this.conta.getNumeroConta()
				+ "\nCPF: " + this.conta.getCliente().getCpf() + "\nSaldo Atual: R$" + this.conta.getSaldo() + "\n");
	}

	public void atualizaTipo() {
		if (this.conta.getSaldo() < 5000.00) {
			this.conta.getCliente().setTipo(TipoCliente.COMUM);
		} else {
			if (this.conta.getSaldo() >= 5000.00 && this.conta.getSaldo() < 15000.00) {
				this.conta.getCliente().setTipo(TipoCliente.SUPER);
			} else {
				this.conta.getCliente().setTipo(TipoCliente.PREMIUM);
			}
		}
	}

	public Conta getConta() {
		return conta;
	}

}
