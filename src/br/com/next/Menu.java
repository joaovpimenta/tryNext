package br.com.next;

import br.com.util.Util;

public class Menu {
	//PRIMEIRO CLIENTE
	static Cliente cliente = null;
	static Conta conta = null;
	static Endereco endereco = null;
	//SEGUNDO CLIENTE
	static Cliente cliente2 = null;
	static Conta conta2 = null;
	static Endereco endereco2 = null;

	public static void menu() {

		chamaObjetoEstatico();

		Util util = new Util();
		
		int i = -1;

		while (i != 0) {

			i = util.readConsoleInt("Seja bem vindo, o que deseja fazer?\n" + "1 - Cadastrar Novo Cliente\n"
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
				util.writeConsole("Cadastro realizado com sucesso!\n");

				break;
			case 2: // Cadastra nova conta
				String senha;
				do {
					senha = util.readConsole("Cadastre uma senha: ");
				} while (!senha.matches("[0-9]{4}"));
				conta2 = new Conta(cliente, senha);

				break;
			case 3: // Realiza Login em conta
					// SE VOCÊ TENTAR LOGAR SEM CRIAR CONTA ANTES, O CÓDIGO EXPLODE

				// tente fazer login

				Integer f = -1;

				while (f != 0) {

					f = util.readConsoleInt(
							"Seja bem vindo," + cliente.getNome() + " o que deseja fazer?\n" + "1 - Transferência\n"
									+ "2 - Saque\n" + "3 - Depósito\n" + "4 - Consultar Saldo\n" + "0 - Sair\n");
					// Switch só para quando estiver logado
					switch (f) {
					case 0: // Encerra o programa

						util.writeConsole("Programa encerrado!");

						break;
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
						util.writeConsole("Cliente: " + cliente.getNome() + "\nConta: " + conta.getNumeroConta()
								+ "\nCPF: " + cliente.getCpf() + "\nSaldo Atual: R$" + conta.saldo + "\n");

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
		endereco = new Endereco("Rua da Alemnha", "1500", "32321123", "Centro", "Antonio Nunes", "MG");
		cliente = new Cliente("12345678999", "João Victor Almeida Pimenta", endereco);
		conta = new ContaCorrente(cliente, "1234");
		conta = new ContaPoupanca(cliente, "1234");
	}

}
