package br.com.next.bo;

import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.TipoCliente;
import br.com.next.bean.TipoConta;
import br.com.next.utils.DataBase;

public class ContaBO {

	private Conta conta;

	public ContaBO(Conta conta) {
		this.conta = conta;
	}

	public ContaBO(Cliente cliente, TipoConta tipoConta) {
		this.conta = this.novaConta(cliente, tipoConta);
	}

	private Conta novaConta(Cliente cliente, TipoConta tipoConta) {
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setNumeroConta();
		conta.setSaldo(0.0);
		conta.setTipoConta(tipoConta);

		DataBase.setContaDB(conta.getNumeroConta(), conta);
		System.out.println("O número da sua conta é: \n" + conta.getNumeroConta() + "\nE o tipo é: " + conta.getTipoConta().toString());

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
			System.out.println("O valor informado é superior ao seu saldo atual.\n");
			return false;
		}
	}

	/**
	 * Esse método deposita um valor na conta informada
	 * 
	 * @param contaDestino    Em qual conta será depositado
	 * @param valorDepositado Quanto será depositado na conta
	 */
	public void depositar(Conta contaDestino, Double valorDepositado) {

		Double saldoDestino = contaDestino.getSaldo();
		saldoDestino += valorDepositado;
		contaDestino.setSaldo(saldoDestino);
		//TODO Mostrar saldo poupança e corrente
		System.out.println("\nSaldo atual: R$" + this.conta.getSaldo() + "\n");
		this.atualizaTipo();

		DataBase.setContaDB(contaDestino.getNumeroConta(), contaDestino);
	}

	public Boolean sacar(Double valorSaque) {

		if (this.conta.getSaldo() >= valorSaque) {
			double saldo = this.conta.getSaldo();
			saldo -= valorSaque;
			this.conta.setSaldo(saldo);

			System.out.println("\nSaldo atual: R$" + this.conta.getSaldo() + "\n");
			this.atualizaTipo();

			DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
			
			return true;
		}else {
			System.out.println("O valor informado é superior ao seu saldo atual.\n");
			return false;
		}
	}

	public void consultaSaldo() { //TODO Mostrar saldo poupança e corrente
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
