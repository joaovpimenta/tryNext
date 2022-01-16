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
				// TODO MENU PRINCIPAL - 1 - CRIAR CONTA
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

				Cliente cliente = clienteBO.cadastrarCliente(cpf, nome, dataNascimento, endereco, senha);

				i = -1;

				while (i != 0) {
					menu.menuCriacaoConta();
					i = util.readConsoleInt();

					List<Conta> listaContas = DataBase.returnContasByCpf(cpf);

					for (Conta contaIteradora : listaContas) {

						if (i == contaIteradora.getTipoConta().getId()) {
							util.writeConsole("Essa conta já existe!\n" + "A conta existente é: \n"
									+ contaIteradora.getNumeroConta() + "\n");
							i = -1;
							break;
						}

					}

					switch (i) {
					case 1:
						// TODO MENU CRIAÇÃO DE CONTA - 1 - CONTA CORRENTE
						new ContaBO(cliente, TipoConta.CORRENTE);
						continue;
					case 2:
						// TODO MENU CRIAÇÃO DE CONTA - 2 - CONTA POUPANÇA
						new ContaBO(cliente, TipoConta.POUPANCA);
						continue;
					case 0:
						// MENU LOGADO - 0 - Voltar ao menu anterior
						break;
					default:
						util.writeConsole("Opção Inválida!\n");
						continue;
					}

				}

				i = -1;
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
							// MENU LOGADO - 1 - Realizar Depósito
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
							// MENU LOGADO - 2 - Realizar Saque
							Double valorSaque = util.readConsoleDouble("Qual o valor do saque?");
							contaBO.sacar(valorSaque);

							continue;
						case 3:
							// MENU LOGADO - 3 - Realizar Transferência
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
							// MENU LOGADO - 4 - Consultar Saldo
							contaBO.consultaSaldo();

							continue;
						case 5:
							// TODO MENU LOGADO - 5 - Area Pix
							System.out.println(
									"Area Pix acabou de sair do forno, mas estão muito quentes para ser servido, tente mais tarde");
							continue;
						case 6:
							// TODO MENU LOGADO - 6 - Criar outra Conta
							System.out.println(
									"Criar outra conta acabou de sair do forno, mas estão muito quentes para ser servido, tente mais tarde");
							continue;
						case 0:
							// MENU LOGADO - 0 - Sair da conta
							System.out.println("Até mais!");
							i = 0;
							break;
						default:
							continue;
						}

					}

				} else {
					System.out.println("Login ou senha incorretos! Tente novamente");
				}
				i = -1;
				continue;
			case 3:
				// TODO MENU PRINCIPAL - TRANSFERÊNCIA SEM FAZER LOGIN
				Conta contaDestino = DataBase.getContaDB(util.readConsole("Informe a conta em que deseja depositar: "));
				if (contaDestino != null) {
					if (contaDestino.getNumeroConta() != null) {
						Double valorDepositado = util.readConsoleDouble("Qual o valor do depósito?");
						// contaBO.depositar(contaDestino, valorDepositado);
					}
				}
				continue;
			case 0:
				// MENU PRINCIPAL - 0 - Sair do sistema
				break;
			default:
				System.out.println("Opção Inválida");
				continue;
			}

		}

	}

}
