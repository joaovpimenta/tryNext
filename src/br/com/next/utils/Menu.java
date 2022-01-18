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
		System.out.println("| » 5 - Área Pix                           |");
		System.out.println("| » 6 - Área de Cartões                    |");
		System.out.println("| » 7 - Criar Conta Poupança               |");
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
	
	public static void menuCartoes() {
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|             × Área Cartões ×             |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Cartão de Crédito                  |");
		System.out.println("| » 2 - Cartão de Débito                   |");
		System.out.println("| » 3 - Solicitar Cartão                   |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");
	}
	
	public static void menuCartaoCredito() {
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|            × Cartão Crédito ×            |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Compra com Crédito                 |");
		System.out.println("| » 2 - Consultar Fatura                   |");
		System.out.println("| » 3 - Solicitar Aumento do Limite        |");
		System.out.println("| » 4 - Bloquear Cartão                    |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");
	}
	
	public static void menuCartaoDebito() {
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|           × Cartão de Débito ×           |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Compra com Débito                  |");
		System.out.println("| » 2 - Consultar Extrato                  |");
		System.out.println("| » 3 - Alterar Limite por Transação       |");
		System.out.println("| » 4 - Bloquear Cartão                    |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");
	}
	
	public static void menuSolicitarCartao() {
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("|           × Solicitar Cartão ×           |");
		System.out.println("+——————————————————————————————————————————+");
		System.out.println("| » 1 - Cartão de Crédito                  |");
		System.out.println("| » 2 - Cartão de Débito                   |");
		System.out.println("| » 0 - Voltar                             |");
		System.out.println("+——————————————————————————————————————————+");
	}

}