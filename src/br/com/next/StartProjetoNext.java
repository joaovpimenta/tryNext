package br.com.next;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.Endereco;
import br.com.next.bean.TipoConta;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;
import br.com.next.bo.ValidacoesBO;
import br.com.next.utils.DataBase;
import br.com.next.utils.Menu;
import br.com.next.utils.Util;

public class StartProjetoNext {

	public static Map<String, Cliente> mapCliente = new HashMap<String, Cliente>();

	public static void main(String[] args) {

		Util util = new Util();
		Menu menu = new Menu();
		ValidacoesBO validacoesBO = new ValidacoesBO();

		int i = -1;

		while (i != 0) {

			menu.menuPrincipal();
			i = util.readConsoleInt();

			switch (i) {
			case 1:

				ClienteBO clienteBO = new ClienteBO();
				String cpf = "-";
				while (validacoesBO.validaCPF(cpf)) {
					cpf = util.readConsole("Digite o número de CPF: ");
				}

				String nome = util.readConsole("Digite o seu nome completo: ");

				String data = "";

				while (validacoesBO.validaData(data)) {
					data = util.readConsole("Informe sua data de nascimento (dd/mm/yyyy): ");
				}
				Date dataNascimento = util.readConsoleData(data);

				String logradouro = "-";
				while (validacoesBO.validaLogradouro(logradouro)) {
					logradouro = util.readConsole("Informe o seu Logradouro: ");
				}

				String numeroLogradouro = "-";
				while (validacoesBO.validaNumero(numeroLogradouro)) {
					numeroLogradouro = util.readConsole("Informe o número do Logradouro: ");
				}

				String cep = "-";
				while (validacoesBO.validaCep(cep)) {
					cep = util.readConsole("Informe seu CEP: ");
				}

				String bairro = util.readConsole("Informe o Bairro: ");
				String cidade = util.readConsole("Informe a Cidade: ");

				String estado = "-";
				while (validacoesBO.validaEstado(estado)) {
					estado = util.readConsole("Informe o Estado: ");
				}

				String senha = "-";
				while (validacoesBO.validaSenha(senha)) {
					senha = util.readConsole("Cadastre uma senha: ");
				}

				Endereco endereco = new Endereco(logradouro, numeroLogradouro, cep, bairro, cidade, estado);

				i = -1;

				while (i != 0) {
					menu.menuCriacaoConta();
					i = util.readConsoleInt("Qual tipo de conta: ");
					
					List<Conta> listaContas = DataBase.returnContasByCpf(cpf);
					
					for(Conta conta : listaContas) {
						
					}

					switch (i) {
					case 1:
						// CC
						new ContaBO(clienteBO.cadastrarCliente(cpf, nome, dataNascimento, endereco, senha),
								TipoConta.CORRENTE);
						continue;
					case 2:
						// CP
						new ContaBO(clienteBO.cadastrarCliente(cpf, nome, dataNascimento, endereco, senha),
								TipoConta.POUPANCA);
						continue;
					case 0:
						// Voltar
						break;
					default:
						util.writeConsole("Opção Inválida!");
						continue;
					}

				}

				System.out.println("Cadastro Realizado com sucesso!");

				continue;
			case 2:

				i = -1;

				String loginCpf = "-";
				while (validacoesBO.validaCPF(loginCpf)) {
					loginCpf = util.readConsole("Digite o seu CPF: ");
				}

				String loginSenha = "-";
				while (validacoesBO.validaSenha(loginSenha)) {
					loginSenha = util.readConsole("Digite sua senha: ");
				}

				ContaBO contaBO = new ContaBO(DataBase.returnContaByCpfSenha(loginCpf, loginSenha));

				if (contaBO.getConta() != null) {

					while (i != 0) {

						menu.menuLogado();
						i = util.readConsoleInt();

						switch (i) {
						case 1:
							// 1 - Realizar Depósito
							Conta contaDestino = DataBase
									.getContaDB(util.readConsole("Informe a conta em que deseja depositar: "));
							if (contaDestino != null) {
								if (contaDestino.getNumeroConta() != null) {
									Double valorDepositado = util.readConsoleDouble("Qual o valor do depósito?");
									contaBO.depositar(contaDestino, valorDepositado);
								}
							}
							continue;
						case 2:
							// 2 - Realizar Saque
							Double valorSaque = util.readConsoleDouble("Qual o valor do saque?");
							contaBO.sacar(valorSaque);

							continue;
						case 3:
							// 3 - Realizar Transferência
							contaDestino = DataBase
									.getContaDB(util.readConsole("Informe a conta para qual deseja transferir: "));
							if (contaDestino != null) {
								if (contaDestino.getNumeroConta() != null) {
									Double valor = util.readConsoleDouble("Qual o valor da transferência?");
									contaBO.transferir(contaDestino, valor);
								}
							}
							continue;
						case 4:
							// 4 - Consultar Saldo
							contaBO.consultaSaldo();

							continue;
						case 5:
							// 5 - Area Pix
							System.out.println(
									"Area Pix acabou de sair do forno, mas estão muito quentes para ser servido, tente mais tarde");
							continue;
						case 6:
							// 6 - Criar Conta Poupança
							System.out.println(
									"Criar Poupança acabou de sair do forno, mas estão muito quentes para ser servido, tente mais tarde");
							continue;
						case 7:
							// 7 - Sair da Conta
							System.out.println("Até mais!");
							continue;
						case 0:
							break;
						default:
							continue;
						}

					}

				} else {
					System.out.println("Login ou senha incorretos! Tente novamente");
				}

				continue;
			case 3:
				System.out.println(
						"Outras opções acabaram de sair do forno, mas estão muito quentes para serem servidas, tente mais tarde");
				continue;
			case 0:
				break;
			default:
				System.out.println("Opção Inválida");
				continue;
			}

		}

	}

}
