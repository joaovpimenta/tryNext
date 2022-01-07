package br.com.next;

import java.util.ArrayList;

import br.com.util.Util;

public class Menu {
	// PRIMEIRO CLIENTE
	static Cliente cliente = null;
	static ContaCorrente contaCorrente = null;
	static ContaPoupanca contaPoupanca = null;
	static Endereco endereco = null;
	// SEGUNDO CLIENTE
	static Cliente cliente2 = null;
	static ContaCorrente contaCorrente2 = null;
	static ContaPoupanca contaPoupanca2 = null;
	static Endereco endereco2 = null;

	static ArrayList<Cliente> listaClientes = new ArrayList<>();

	static ArrayList<ContaCorrente> listaContaCorrente = new ArrayList<>();

	static ArrayList<ContaPoupanca> listaContaPoupanca = new ArrayList<>();

	public static void menu() {

		chamaObjetoEstatico();

		Util util = new Util();

		int i = -1;

		while (i != 0) {

			i = util.readConsoleInt("Seja bem vinda(o), o que deseja fazer?\n" + "1 - Cadastrar Nova(o) Cliente\n"
					+ "2 - Cadastrar Conta\n" + "3 - Realizar Login\n" + "0 - Sair\n");

			switch (i) {
			case 0: // Encerra o programa

				util.writeConsole("Programa encerrado!");

				break;
			case 1: // Cadastra novo cliente

				String cpf;
				Endereco endereco;
				do {
					cpf = util.readConsole("Informe seu CPF: ");
				} while (!cpf.matches("[0-9]{11}"));
				String nome = util.readConsole("Informe seu Nome Completo: ");

				String logradouro = util.readConsole("Informe o seu Logradouro: ");
				String numero = util.readConsole("Informe o número do Logradouro: ");
				String cep = util.readConsole("Informe seu CEP: ");
				String bairro = util.readConsole("Informe o Bairro: ");
				String cidade = util.readConsole("Informe a Cidade: ");
				String estado = util.readConsole("Informe o Estado: ");

				endereco = new Endereco(logradouro, numero, cep, bairro, cidade, estado);

				cliente2 = new Cliente(cpf, nome, endereco);
				listaClientes.add(cliente2);
				util.writeConsole("Cadastro realizado com sucesso!\n");

				break;
			case 2: // Cadastra nova conta
				String senha;
				do {
					senha = util.readConsole("Cadastre uma senha: ");
				} while (!senha.matches("[0-9]{4}"));
				contaCorrente2 = new ContaCorrente(cliente, senha);
				listaContaCorrente.add(contaCorrente2);
				contaPoupanca2 = new ContaPoupanca(cliente, senha);
				listaContaPoupanca.add(contaPoupanca2);

				break;
			case 3: // Realiza Login em conta
					// SE VOCÊ TENTAR LOGAR SEM CRIAR CONTA ANTES, O CÓDIGO EXPLODE

				// tente fazer login

				Integer f = -1;
				Integer tipoConta;

				while (f != 0) {

					f = util.readConsoleInt(
							"Seja bem vinda(o), " + cliente.getNome() + " o que deseja fazer?\n" + "1 - Transferência\n"
									+ "2 - Saque\n" + "3 - Depósito\n" + "4 - Consultar Saldo\n" + "0 - Sair\n");
					// Switch só para quando estiver logado
					switch (f) {
					case 0: // Encerra o programa

						util.writeConsole("Programa encerrado!");

						break;
					case 1:

						tipoConta = util.readConsoleInt("Escolha a conta de origem da transferência:\n"
								+ "1 - Conta Corrente\n" + "2 - Conta Poupança\n" + "0 - Voltar\n");

						switch (tipoConta) {
						case 0:

							break;
						case 1:
							contaCorrente.transferir();
							break;
						case 2:
							contaPoupanca.transferir();
							break;
						default:
							util.writeConsole("Opção Inválida");
							break;
						}

						break;

					case 2:

						tipoConta = util.readConsoleInt("Escolha a conta de origem do saque:\n" + "1 - Conta Corrente\n"
								+ "2 - Conta Poupança\n" + "0 - Voltar\n");

						switch (tipoConta) {
						case 0:

							break;
						case 1:
							contaCorrente.saque();
							break;
						case 2:
							contaPoupanca.saque();
							break;
						default:
							util.writeConsole("Opção Inválida");
							break;
						}

						break;
					case 3:
						tipoConta = util.readConsoleInt("Escolha a conta em que deseja depositar:\n"
								+ "1 - Conta Corrente\n" + "2 - Conta Poupança\n" + "0 - Voltar\n");

						switch (tipoConta) {
						case 0:

							break;
						case 1:
							contaCorrente.depositar();
							break;
						case 2:
							contaPoupanca.depositar();
							break;
						default:
							util.writeConsole("Opção Inválida");
							break;
						}

						break;
					case 4:

						tipoConta = util.readConsoleInt("Escolha a conta em que deseja consultar saldo:\n"
								+ "1 - Conta Corrente\n" + "2 - Conta Poupança\n" + "0 - Voltar\n");

						switch (tipoConta) {
						case 0:

							break;
						case 1:
							contaCorrente.consultarSaldo();
							util.writeConsole("Cliente: " + cliente.getNome() + "\nConta: "
									+ contaCorrente.getNumeroConta() + "\nCPF: " + cliente.getCpf()
									+ "\nSaldo Atual: R$" + contaCorrente.saldo + "\n");
							break;
						case 2:
							contaPoupanca.consultarSaldo();
							util.writeConsole("Cliente: " + cliente.getNome() + "\nConta: "
									+ contaCorrente.getNumeroConta() + "\nCPF: " + cliente.getCpf()
									+ "\nSaldo Atual: R$" + contaCorrente.saldo + "\n");
							break;
						default:
							util.writeConsole("Opção Inválida");
							break;
						}

						break;
					default:
						util.writeConsole("Opção Inválida!");
						break;
					}
				}
				break;

			default:
				util.writeConsole("Opção Inválida!");
				break;

			}
		}
	}

	private static void chamaObjetoEstatico() {
		endereco = new Endereco("Rua da Alemanha", "1500", "32321123", "Centro", "Antonio Nunes", "MG");
		cliente = new Cliente("12345678999", "João Victor Almeida Pimenta", endereco);
		listaClientes.add(cliente);
		contaCorrente = new ContaCorrente(cliente, "1234");
		listaContaCorrente.add(contaCorrente);
		contaPoupanca = new ContaPoupanca(cliente, "1234");
		listaContaPoupanca.add(contaPoupanca);
	}

}
