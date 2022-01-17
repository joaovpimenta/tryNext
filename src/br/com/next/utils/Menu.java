package br.com.next.utils;

public class Menu {

	Util util = new Util();

	public static void menuPrincipal() {

		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|             × Menu Inicial ×             |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Criar Conta                        |");
		System.out.println("| » 2 - Fazer Login                        |");
		System.out.println("| » 3 - Depósito sem Login                 |");
		System.out.println("| » 0 - Sair                               |");
		System.out.println("+——————————————————————————————————————————+");

	}

	public static void menuCriacaoConta() {

		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|           × Criação de conta ×           |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Criar Conta Corrente               |");
		System.out.println("| » 2 - Criar Conta Poupança               |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");

	}

	public static void menuLogado() {

		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|            × Seja Bem-Vindo ×            |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Realizar Depósito                  |"); // TODO Criar submenu - transações (Saques, Transferência e Depósito).
		System.out.println("| » 2 - Realizar Saque                     |");
		System.out.println("| » 3 - Realizar Transferência             |");
		System.out.println("| » 4 - Consultar Saldo                    |");
		System.out.println("| » 5 - Area Pix                           |");
		System.out.println("| » 6 - Criar Conta Poupança               |");
		System.out.println("| » 0 - Sair do Sistema                    |");
		System.out.println("+——————————————————————————————————————————+");

	}

	public static void menuPix() {
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|               × Área Pix ×               |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Consultar Chave                    |");
		System.out.println("| » 2 - Cadastrar Chave                    |");
		System.out.println("| » 3 - Transferência via Pix              |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");
	}

	public static void menuCadastroPix() {
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|             × Cadastro Pix ×             |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Cadastrar Chave CPF                |");
		System.out.println("| » 2 - Cadastrar Chave EMAIL              |");
		System.out.println("| » 3 - Cadastrar Chave TELEFONE           |");
		System.out.println("| » 4 - Cadastrar Chave ALEATÓRIA          |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");
	}

}