package br.com.next.bo;

import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.utils.DataBase;

public class ContaBO {

	private Conta conta;

	public ContaBO(Conta conta) {
		this.conta = conta;
	}

	public ContaBO(Cliente cliente) {
		this.conta = this.gerarConta(cliente);

	}

	private Conta gerarConta(Cliente cliente) {
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setNumeroConta();
		conta.setSaldo(0.0);
		
		DataBase.insertConta(conta.getNumeroConta(), conta);
		
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
			this.conta.atualizaTipo();
			
			DataBase.insertConta(contaDestino.getNumeroConta(), contaDestino);
			DataBase.insertConta(this.conta.getNumeroConta(), this.conta);

			return true;

		} else {
			System.out.println("O valor informado é superior ao seu saldo atual.\n");
			return false;
		}
	}
	public void depositar(double valorDepositado) {
		Double saldoAtual = this.conta.getSaldo();
		saldoAtual += valorDepositado;
		this.conta.setSaldo(saldoAtual);
		DataBase.insertConta(this.conta.getNumeroConta(), this.conta);
		
		System.out.println("\nSaldo atual: R$" + this.conta.getSaldo() + "\n");
		this.conta.atualizaTipo();
	}
	public void consultaSaldo() {
		System.out.println("Cliente: " + this.conta.getCliente().getNome() + "\nConta: " + this.conta.getNumeroConta() + "\nCPF: "
				+ this.conta.getCliente().getCpf() + "\nSaldo Atual: R$" + this.conta.getSaldo() + "\n");
	}
}
