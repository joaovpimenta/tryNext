package br.com.next;

import br.com.util.Util;

public class Main {

	public static void main(String[] args) {

		Util util = new Util();
		Cliente cliente = new Cliente();
		Conta conta = new Conta();
		Menu menu = new Menu();
		
		Menu.menu();

		/*int i;

		i = util.readConsoleInt("Seja bem vindo, o que deseja fazer?\n" + "1 - Cadastrar Novo Cliente\n"
				+ "2 - Cadastrar Conta\n" + "3 - \n" + "4 - \n" + "5 - \n");

		switch (i) {
		case 1:

			cliente.cadastrarDados();
			conta.cadastrarConta(cliente);

			break;
		case 2:

			conta.cadastrarConta(cliente);

			break;
		default:
			util.writeConsole("Opção Inválida!");
			break;
		}*/
		
		System.out.println("teste commit com git gui");
		
	}

}
