package br.com.next;

import br.com.util.Util;

public class Menu {

	static Cliente cliente = null;
	static Conta conta = null;
	static Cliente cliente2 = null;
	static Conta conta2 = null;

	public static void menu() {

		chamaObjetoEstatico();

		Util util = new Util();

		// Conta conta = new Conta(cliente);

		int i = -1;

		while (i != 0) {

			i = util.readConsoleInt("Seja bem vindo, o que deseja fazer?\n" + "1 - Cadastrar Novo Cliente\n"
					+ "2 - Cadastrar Conta\n" + "3 - Realizar Login\n" + "0 - Sair\n");

			switch (i) {
			case 0: //Encerra o programa

				util.writeConsole("Programa encerrado!");

				break;
			case 1: //Cadastra novo cliente

				String cpf;
				do {
					cpf = util.readConsole("Informe seu CPF: ");
				} while (!cpf.matches("[0-9]{11}"));
				String nome = util.readConsole("Informe seu Nome Completo: ");
				cliente = new Cliente(cpf, nome);
				util.writeConsole("Cadastro realizado com sucesso!\n");

				break;
			case 2: //Cadastra nova conta
				String senha;
				do {
					senha = util.readConsole("Cadastre uma senha: ");
				} while (!senha.matches("[0-9]{4}"));
				conta = new Conta(cliente, senha);

				break;
			case 3: //Realiza Login em conta
				
				// tente fazer login
				
				
				Integer f = -1;

				while (f != 0) {

					f = util.readConsoleInt(
							"Seja bem vindo," + cliente.getNome() + " o que deseja fazer?\n" + "1 - Transferência\n"
									+ "2 - Saque\n" + "3 - Depósito\n" + "4 - Consultar Saldo\n" + "0 - Sair\n");
					// Switch só para quando estiver logado
					switch (f) {
					case 1:

						conta.transferir();

						break;

					case 2:

						conta.saque();

						break;
					case 3:

						conta.depositar();

						break;
					case 4:

						conta.consultarSaldo();

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

		cliente2 = new Cliente("12345678999", "joaozinho");
		conta2 = new ContaCorrente(cliente2, "1234");
		conta2 = new ContaPoupanca(cliente2, "1234");
	}

}
