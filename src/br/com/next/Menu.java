package br.com.next;

import br.com.util.Util;

public class Menu {

	public static void menu() {

		Util util = new Util();
		Cliente cliente = new Cliente();
		Conta conta = new Conta();

		int i = 0;

		while (i != 10) {

			i = util.readConsoleInt("Seja bem vindo, o que deseja fazer?\n" + "1 - Cadastrar Novo Cliente\n"
					+ "2 - Cadastrar Conta\n" + "3 - Transferência\n" + "4 - Saque\n" + "5 - Depósito\n" + "10 - Sair\n");

			switch (i) {
			case 1:

				cliente.cadastrarDados();
				conta.cadastrarConta(cliente);

				break;
			case 2:

				conta.cadastrarConta(cliente);

				break;
			case 3:

				conta.transferir();

				break;
			case 4:

				conta.saque();

				break;
			case 5:

				conta.depositar();

				break;
			case 6:

				conta.consultarSaldo();

				break;
			default:
				util.writeConsole("Opção Inválida!");
				break;
			}
		}
	}

}
