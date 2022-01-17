package br.com.next.bo;

import java.util.Calendar;
import java.util.Date;

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

		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.MONTH, 1);
		Date dataExecucao = calendario.getTime();

		conta.setDataExecucao(dataExecucao);

		DataBase.setContaDB(conta.getNumeroConta(), conta);
		System.out.println("O n�mero da sua conta �: \n" + conta.getNumeroConta() + "\nE o tipo �: "
				+ conta.getTipoConta().toString());

		return conta;
	}

	public boolean transferir(Conta contaDestino, double valor) {

		if (this.conta.getSaldo() >= valor) {
			double saldo = this.conta.getSaldo();
			saldo -= valor;
			if (this.conta.getTipoConta() != contaDestino.getTipoConta()) {
				saldo -= 5.6;
			}
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
	public void depositar(Conta contaDestino, Double valorDepositado, Boolean isLogged) {

		Double saldoDestino = contaDestino.getSaldo();
		saldoDestino += valorDepositado;
		contaDestino.setSaldo(saldoDestino);
		// TODO Mostrar saldo poupan�a e corrente
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
			System.out.println("O valor informado � superior ao seu saldo atual.\n");
			return false;
		}
	}

	public void consultaSaldo() { // TODO Mostrar saldo poupan�a e corrente
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

	public void executarTaxasOuAcrescimos() {

		if (this.conta.getDataExecucao().before(new Date())) {
			if (this.conta.getTipoConta() == TipoConta.CORRENTE) {
				double saldo = this.conta.getSaldo();
				saldo -= (saldo * 0.45);
				this.conta.setSaldo(saldo);
				DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);

			}
			if (this.conta.getTipoConta() == TipoConta.POUPANCA) {
				double saldo = this.conta.getSaldo();
				saldo += (saldo * 0.03);
				this.conta.setSaldo(saldo);
				DataBase.setContaDB(this.conta.getNumeroConta(), this.conta);
			} else {
				System.out.println("Conta sem Tipo n�o tem taxas nem rendimentos\n\n");
			}

		}

	}

}
