package br.com.next.bo;

import java.util.Calendar;
import java.util.Date;

import br.com.next.bean.CartaoCredito;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoCliente;
import br.com.next.bean.TipoConta;
import br.com.next.utils.DataBase;
import br.com.next.utils.Util;

public class ContaBO {

	private Conta conta;

	public ContaBO(Conta conta) {
		this.conta = conta;
	}

	public ContaBO(Cliente cliente, TipoConta tipoConta) {
		this.conta = this.novaConta(cliente, tipoConta);
	}

	private Conta novaConta(Cliente cliente, TipoConta tipoConta) {
		Util.loading();
		
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setNumeroConta();
		conta.setSaldo(0.0);
		conta.setTipoConta(tipoConta);

		Date dataExecucao = this.avancaMes();
		conta.setDataExecucao(dataExecucao);

		DataBase.setContaDB(conta.getNumeroConta(), conta);
		
		System.out.println("O número da sua conta é: \n" + conta.getNumeroConta() + "\nE o tipo é: "
				+ conta.getTipoConta().toString());

		return conta;
	}

	public boolean transferir(Conta contaDestino, double valor) {
		Util.loading();
		
		double saldo = this.conta.getSaldo();
		double saldoDestino = contaDestino.getSaldo();

		if (this.conta.getTipoConta() != contaDestino.getTipoConta()) {
			if (saldo >= (valor + 5.6)) {
				saldo -= (valor + 5.6);
				saldoDestino += valor;
			} else {
				System.out.println("O valor informado acrescido de taxa é superior ao seu saldo atual.\n");
				return false;
			}
		} else if (saldo >= valor) {
			saldo -= valor;
			saldoDestino += valor;
		} else {
			System.out.println("O valor informado é superior ao seu saldo atual.\n");
			return false;
		}

		contaDestino.setSaldo(saldoDestino);
		this.conta.setSaldo(saldo);
		this.atualizaTipo();
		System.out.println("Saldo atual: R$" + this.conta.getSaldo() + "\n");

		DataBase.setContaDB(contaDestino.getNumeroConta(), contaDestino);
		DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);

		return true;

	}

	public boolean transferirViaPix(Conta contaDestino, double valorPix) {
		Util.loading();
		
		double saldo = this.conta.getSaldo();
		double saldoDestino = contaDestino.getSaldo();

		if (saldo >= valorPix) {
			saldo -= valorPix;
			saldoDestino += valorPix;
		} else {
			System.out.println("O valor informado é superior ao seu saldo atual.\n");
			return false;
		}

		contaDestino.setSaldo(saldoDestino);
		this.conta.setSaldo(saldo);
		this.atualizaTipo();
		System.out.println("Saldo atual: R$" + this.conta.getSaldo() + "\n");

		DataBase.setContaDB(contaDestino.getNumeroConta(), contaDestino);
		DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);

		return true;

	}

	/**
	 * Esse método deposita um valor na conta informada
	 * 
	 * @param contaDestino    Em qual conta será depositado
	 * @param valorDepositado Quanto será depositado na conta
	 */
	public void depositar(Conta contaDestino, Double valorDepositado, Boolean isLogged) {
		Util.loading();

		Double saldoDestino = contaDestino.getSaldo();
		saldoDestino += valorDepositado;
		contaDestino.setSaldo(saldoDestino);
		// TODO Mostrar saldo poupança e corrente
		if (isLogged) {
			System.out.println("\nSaldo atual: R$" + this.conta.getSaldo() + "\n");
		}
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
		} else {
			System.out.println("O valor informado é superior ao seu saldo atual.\n");
			return false;
		}
	}

	public void consultaSaldo() { // TODO Mostrar saldo poupança e corrente
		Util.loading();
		
		System.out.println("Cliente: " + this.conta.getCliente().getNome());
		System.out.println("Conta: " + this.conta.getNumeroConta() + "\nCPF: " + this.conta.getCliente().getCpf());
		System.out.println("\nSaldo Atual: R$" + this.conta.getSaldo() + "\n");
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

	public Date avancaMes() {
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.MONTH, 1);
		Date dataExecucao = calendario.getTime();
		return dataExecucao;
	}

	public void executarTaxasOuAcrescimos() {

		if (this.conta.getDataExecucao().before(new Date())) {
			if (this.conta.getTipoConta() == TipoConta.CORRENTE) {
				double saldo = this.conta.getSaldo();
				saldo -= (saldo * 0.0045);
				this.conta.setSaldo(saldo);
				DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);

			}
			if (this.conta.getTipoConta() == TipoConta.POUPANCA) {
				double saldo = this.conta.getSaldo();
				saldo += (saldo * 0.003);
				this.conta.setSaldo(saldo);
				DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
			} else {
				System.out.println("Conta sem Tipo não tem taxas nem rendimentos\n\n");
			}

			Date dataExecucao = this.avancaMes();
			this.conta.setDataExecucao(dataExecucao);
			DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);

		}

	}

	public void adicionarPix(Pix pix) {
		this.conta.setPix(pix);
		DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
	}

	public Cliente getCliente() {
		return this.conta.getCliente();
	}

	public void adicionarCartaoCredito(CartaoCredito cartaoCredito) {
		this.conta.setCartaoCredito(cartaoCredito);
		DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
	}

	public void adicionarCartaoDebito(CartaoDebito cartaoDebito) {
		this.conta.setCartaoDebito(cartaoDebito);
		DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
	}
}
