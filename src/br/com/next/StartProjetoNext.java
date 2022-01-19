package br.com.next;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.next.bean.CartaoCredito;
import br.com.next.bean.CartaoDebito;
import br.com.next.bean.Cliente;
import br.com.next.bean.Conta;
import br.com.next.bean.Endereco;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoChavePix;
import br.com.next.bean.TipoConta;
import br.com.next.bo.CartaoCreditoBO;
import br.com.next.bo.CartaoDebitoBO;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;
import br.com.next.bo.ValidacoesBO;
import br.com.next.utils.DataBase;
import br.com.next.utils.Menu;
import br.com.next.utils.Util;

public class StartProjetoNext {

	public static Map<String, Cliente> mapCliente = new HashMap<String, Cliente>();

	public static void main(String[] args) {

		ValidacoesBO validacoesBO = new ValidacoesBO();
		
		Util.loading(21);

		int i = -1;

		while (i != 0) {

			Menu.menuPrincipal();
			i = Util.readConsoleInt();

			switch (i) {
			case 1: //MENU PRINCIPAL - 1 - CRIAR CONTA
				ClienteBO clienteBO = new ClienteBO();
				String cpf = "-";
				while (validacoesBO.validaCPF(cpf)) {
					cpf = Util.readConsole("Digite o número de CPF: ");
				}

				String nome = Util.readConsole("Digite o seu nome completo: ");

				String data = "";

				while (validacoesBO.validaData(data)) {
					data = Util.readConsole("Informe sua data de nascimento (dd/mm/yyyy): ");
				}
				Date dataNascimento = Util.readConsoleData(data);

				String logradouro = "-";
				while (validacoesBO.validaLogradouro(logradouro)) {
					logradouro = Util.readConsole("Informe o seu Logradouro: ");
				}

				String numeroLogradouro = "-";
				while (validacoesBO.validaNumero(numeroLogradouro)) {
					numeroLogradouro = Util.readConsole("Informe o número do Logradouro: ");
				}

				String cep = "-";
				while (validacoesBO.validaCep(cep)) {
					cep = Util.readConsole("Informe seu CEP: ");
				}

				String bairro = Util.readConsole("Informe o Bairro: ");
				String cidade = Util.readConsole("Informe a Cidade: ");

				String estado = "-";
				while (validacoesBO.validaEstado(estado)) {
					estado = Util.readConsole("Informe o Estado: ").toUpperCase();
				}

				String senha = "-";
				while (validacoesBO.validaSenha(senha)) {
					senha = Util.readConsole("Cadastre uma senha: ");
				}

				Endereco endereco = new Endereco(logradouro, numeroLogradouro, cep, bairro, cidade, estado);

				Cliente cliente = clienteBO.cadastrarCliente(cpf, nome, dataNascimento, endereco, senha);
				
				Util.loading(21);
				
				i = -1;

				while (i != 0) {
					Menu.menuCriacaoConta();
					i = Util.readConsoleInt();

					List<Conta> listaContas = DataBase.returnContasByCpf(cpf);

					for (Conta contaIteradora : listaContas) {

						if (i == contaIteradora.getTipoConta().getId()) {
							Util.writeConsole("Essa conta já existe!\n" + "A conta existente é: \n"
									+ contaIteradora.getNumeroConta() + "\n");
							i = -1;
							break;
						}

					}

					switch (i) {
					case 1:
						// »MENU CRIAÇÃO DE CONTA - 1 - CONTA CORRENTE
						new ContaBO(cliente, TipoConta.CORRENTE);
						Util.printLoading(21);
						continue;
					case 2:
						// »MENU CRIAÇÃO DE CONTA - 2 - CONTA POUPANÇA
						new ContaBO(cliente, TipoConta.POUPANCA);
						Util.printLoading(21);
						continue;
					case 0:
						// »MENU CRIAÇÃO DE CONTA - 0 - VOLTAR AO MENU ANTERIOR
						break;
					default:
						Util.writeConsole("Opção Inválida!\n");
						continue;
					}

				}

				i = -1;

				continue;
			case 2: // »MENU PRINCIPAL - LOGIN

				i = -1;

				String loginCpf = "-";
				while (validacoesBO.validaCPF(loginCpf)) {
					loginCpf = Util.readConsole("Digite o seu CPF: ");
				}

				String loginSenha = "-";
				while (validacoesBO.validaSenha(loginSenha)) {
					loginSenha = Util.readConsole("Digite sua senha: ");
				}
				
				ContaBO contaCorrenteBO = null;
				ContaBO contaPoupancaBO = null;

				List<Conta> listaContas = DataBase.returnContasByCpfSenha(loginCpf, loginSenha);

				for (Conta conta : listaContas) {

					if (conta.getTipoConta() == TipoConta.CORRENTE) {
						contaCorrenteBO = new ContaBO(conta);
						continue;
					} else {
						contaPoupancaBO = new ContaBO(conta);
					}

				}

				if (contaCorrenteBO != null || contaPoupancaBO != null) {

					while (i != 0) {

						Menu.menuLogado();
						i = Util.readConsoleInt();

						switch (i) {
						case 1: // »MENU LOGADO - 1 - REALIZAR TRANSAÇÕES

							while (i != 0) {
								Menu.menuTransacoes();
								i = Util.readConsoleInt();

								switch (i) {
								case 1:
									// »MENU TRANSAÇÕES - 1 - REALIZAR DEPÓSITO
									Conta contaDestino = DataBase.getContaDB(
											Util.readConsoleInt("Informe a conta em que deseja depositar: "));
									if (contaDestino != null) {
										if (contaDestino.getNumeroConta() != null) {
											Double valorDepositado = Util
													.readConsoleDouble("Qual o valor do depósito?");
											contaCorrenteBO.depositar(contaDestino, valorDepositado, true);
										}
									}
									continue;
								case 2:
									// »MENU TRANSAÇÕES - 2 - REALIZAR SAQUE
									Double valorSaque = Util.readConsoleDouble("Qual o valor do saque?");
									if (contaCorrenteBO != null && contaPoupancaBO != null) {
										int escolhaConta = Util.readConsoleInt(
												"Qual conta deseja sacar? (1 - CORRENTE, 2 - POUPANÇA)");
										if (escolhaConta == 1) {
											contaCorrenteBO.sacar(valorSaque);
										} else {
											contaPoupancaBO.sacar(valorSaque);
										}
									} else if (contaCorrenteBO != null) {
										contaCorrenteBO.sacar(valorSaque);
									} else {
										contaPoupancaBO.sacar(valorSaque);
									}
									continue;
								case 3:
									// »MENU TRANSAÇÕES - 3 - REALIZAR TRANSFERÊNCIA
									contaDestino = DataBase.getContaDB(
											Util.readConsoleInt("Informe a conta para qual deseja transferir: "));
									if (contaDestino != null) {
										if (contaDestino.getNumeroConta() != null) {
											Double valor = Util.readConsoleDouble("Qual o valor da transferência?");

											if (contaCorrenteBO != null && contaPoupancaBO != null) {
												int escolhaConta = Util.readConsoleInt(
														"De qual conta deseja tranferir? (1 - CORRENTE, 2 - POUPANÇA)");
												if (escolhaConta == 1) {
													contaCorrenteBO.transferir(contaDestino, valor);
												} else {
													contaPoupancaBO.transferir(contaDestino, valor);
												}
											} else if (contaCorrenteBO != null) {
												contaCorrenteBO.transferir(contaDestino, valor);
											} else {
												contaPoupancaBO.transferir(contaDestino, valor);
											}

										}
									}
									continue;
								case 0:
									// »MENU TRANSAÇÕES - 0 - VOLTAR AO MENU ANTERIOR
									break;

								default:
									Util.writeConsole("Opção Inválida!\n");
									continue;
								}
							}
							i = -1;
							continue;
						case 2:
							// »MENU LOGADO - 2 - CONSULTAR SALDO
							if (contaCorrenteBO != null && contaPoupancaBO != null) {

								contaCorrenteBO.consultaSaldo();
								contaPoupancaBO.consultaSaldo();

							} else if (contaCorrenteBO != null) {
								contaCorrenteBO.consultaSaldo();
							} else {
								contaPoupancaBO.consultaSaldo();
							}
							continue;
						case 3:
							// »MENU LOGADO - 3 - ÁREA PIX
							Menu.menuPix();
							i = Util.readConsoleInt();
							switch (i) {
							case 1:
								Util.writeConsole(
										"Consultar Chave Pix acabou de sair do forno, mas está muito quente para ser servido, tente mais tarde");
								continue;
							case 2:

								Pix pix = new Pix();
								pix.setIsAtivado(true);

								Menu.menuCadastroPix();
								i = Util.readConsoleInt();

								switch (i) {
								case 1: // »MENU CADASTRO CHAVE - 1 - CPF
									String chaveCpf = "-";
									while (validacoesBO.validaCPF(chaveCpf)) {
										chaveCpf = Util.readConsole("Digite o número de CPF: ");
									}
									pix.setTipoChavePix(TipoChavePix.CPF);
									pix.setValorChave(chaveCpf);
									continue;
								case 2: // »MENU CADASTRO CHAVE - 2 - EMAIL
									String chaveEmail = "-";
									while (validacoesBO.validaEmail(chaveEmail)) {
										chaveEmail = Util.readConsole("Digite o endereço de Email: ");
									}
									pix.setTipoChavePix(TipoChavePix.EMAIL);
									pix.setValorChave(chaveEmail);
									continue;
								case 3: // »MENU CADASTRO CHAVE - 3 - TELEFONE
									String chaveTelefone = "-";
									while (validacoesBO.validaTelefone(chaveTelefone)) {
										chaveTelefone = Util
												.readConsole("Digite o número de Telefone: (Ex: +55(31)99999-9999 ");
									}
									pix.setTipoChavePix(TipoChavePix.TELEFONE);
									pix.setValorChave(chaveTelefone);
									continue;
								case 4: // »MENU CADASTRO CHAVE - 4 - ALEATÓRIO
									String chaveAleatoria = UUID.randomUUID().toString();
									pix.setTipoChavePix(TipoChavePix.ALEATORIO);
									pix.setValorChave(chaveAleatoria);
									continue;
								case 0:
									// »MENU CADASTRO CHAVE - 0 - VOLTAR AO MENU ANTERIOR
									break;
								default:
									Util.writeConsole("Opção Inválida!\n");
									continue;
								}

								contaCorrenteBO.adicionarPix(pix);
								i = -1;
								continue;
							case 3: // »MENU PIX - 3 - TRANSFERIR VIA PIX

								String chavePix = Util.readConsole("Informe a chave Pix:");
								Double valorPix = Util.readConsoleDouble("Qual o valor da transferência?");

								Conta contaDestino = DataBase.returnContasByChavePix(chavePix);

								if (contaDestino == null) {
									continue;
								}

								contaCorrenteBO.transferirViaPix(contaDestino, valorPix);

								continue;
							case 0:
								// »MENU PIX - 0 - VOLTAR AO MENU ANTERIOR
								break;

							default:
								Util.writeConsole("Opção Inválida!\n");
								continue;
							}
							i = -1;
							continue;
						case 4: //MENU MENU LOGADO - 4 - ÁREA DE CARTÕES
							Menu.menuCartoes();
							i = Util.readConsoleInt();

							if (i == 0) {
								break;
							}

							switch (i) {
							case 1: //MENU ÁREA DE CARTÕES - 1 - CARTÃO DE CRÉDITO
								Menu.menuCartaoCredito();
								i = Util.readConsoleInt();

								if (i == 0) {
									break;
								}

								ContaBO contaDoCartao;

								if (contaCorrenteBO != null && contaPoupancaBO != null) {
									int escolhaConta = Util.readConsoleInt(
											"De qual conta deseja solicitar cartão? (1 - CORRENTE, 2 - POUPANÇA)");
									if (escolhaConta == 1) {
										contaDoCartao = contaCorrenteBO;
									} else {
										contaDoCartao = contaPoupancaBO;
									}
								} else if (contaCorrenteBO != null) {
									contaDoCartao = contaCorrenteBO;
								} else {
									contaDoCartao = contaPoupancaBO;
								}
								
								CartaoCredito cartaoCreditoEmUso = contaDoCartao.getConta().getCartaoCredito(); //TODO movimentar com cartão
								
								continue;
							case 2: //MENU ÁREA DE CARTÕES - 2 - CARTÃO DE DÉBITO
								Menu.menuCartaoDebito();
								i = Util.readConsoleInt();

								if (i == 0) {
									break;
								}

								if (contaCorrenteBO != null && contaPoupancaBO != null) {
									int escolhaConta = Util.readConsoleInt(
											"De qual conta deseja solicitar cartão? (1 - CORRENTE, 2 - POUPANÇA)");
									if (escolhaConta == 1) {
										contaDoCartao = contaCorrenteBO;
									} else {
										contaDoCartao = contaPoupancaBO;
									}
								} else if (contaCorrenteBO != null) {
									contaDoCartao = contaCorrenteBO;
								} else {
									contaDoCartao = contaPoupancaBO;
								}
								
								CartaoDebito  cartaoDebitoEmUso  = contaDoCartao.getConta().getCartaoDebito();
								
								continue;
							case 3: //MENU ÁREA DE CARTÕES - 3 - SOLICITAR CARTÃO
								Menu.menuSolicitarCartao();
								i = Util.readConsoleInt();

								if (i == 0) {
									break;
								}

								if (contaCorrenteBO != null && contaPoupancaBO != null) {
									int escolhaConta = Util.readConsoleInt(
											"De qual conta deseja solicitar cartão? (1 - CORRENTE, 2 - POUPANÇA)");
									if (escolhaConta == 1) {
										contaDoCartao = contaCorrenteBO;
									} else {
										contaDoCartao = contaPoupancaBO;
									}
								} else if (contaCorrenteBO != null) {
									contaDoCartao = contaCorrenteBO;
								} else {
									contaDoCartao = contaPoupancaBO;
								}

								Cliente clienteDoCartao = contaDoCartao.getCliente();

								switch (i) {
								case 1: // »MENU SOLICITAR CARTÃO - 1 - CARTÃO DE CRÉDITO

									CartaoCredito CartaoCreditoExistente = contaCorrenteBO.getConta()
											.getCartaoCredito();

									if (CartaoCreditoExistente != null) {
										Util.writeConsole("Você já tem um cartão de Crédito!");
										continue;
									} else {
										String senhaCartao = "-";
										while (validacoesBO.validaSenha(senhaCartao)) {
											senhaCartao = Util.readConsole("Digite uma senha de 4 digitos: ");
										}
										
										new CartaoCreditoBO(senhaCartao, clienteDoCartao);

											//	CartaoCredito cartaoCredito = cartaoCreditoBO.
											//	new CartaoCredito(senhaCartao, clienteDoCartao);
											//	contaCorrenteBO.adicionarCartaoCredito(cartaoCredito);
									}

									continue;
								case 2: // »MENU SOLICITAR CARTÃO - 2 - CARTÃO DE DÉBITO

									CartaoDebito CartaoDebitoExistente = contaCorrenteBO.getConta().getCartaoDebito();

									if (CartaoDebitoExistente != null) {
										Util.writeConsole("Você já tem um cartão de Débito!");
										continue;
									} else {
										String senhaCartao = "-";
										while (validacoesBO.validaSenha(senhaCartao)) {
											senhaCartao = Util.readConsole("Digite uma senha de 4 digitos: ");
										}

										Double limiteTransacao = Util
												.readConsoleDouble("Defina o limite por transação: ");

										new CartaoDebitoBO(senhaCartao, limiteTransacao, clienteDoCartao);
										//contaCorrenteBO.adicionarCartaoDebito(cartaoDebito);
									}

									continue;
								case 0: // »MENU SOLICITAR CARTÃO - 0 - VOLTAR AO MENU ANTERIOR
									break;
								}
								continue;
							case 0:
								// »MENU CARTÕES - 0 - VOLTAR AO MENU ANTERIOR
								break;
							default:
								Util.writeConsole("Opção Inválida!\n");
								continue;
							}

							continue;
						case 5:
							// »MENU LOGADO - 5 - CRIAR OUTRA CONTA
							i = -1;

							while (i != 0) {
								Menu.menuCriacaoConta();
								i = Util.readConsoleInt();

								listaContas = DataBase.returnContasByCpf(loginCpf);

								for (Conta contaIteradora : listaContas) {

									if (i == contaIteradora.getTipoConta().getId()) {
										Util.writeConsole("Essa conta já existe!\n" + "A conta existente é: \n"
												+ contaIteradora.getNumeroConta() + "\n");
										i = -1;
										break;
									}

								}

								switch (i) {
								case 1:
									// »MENU CRIAÇÃO DE CONTA - 1 - CONTA CORRENTE
									new ContaBO(contaPoupancaBO.getCliente(), TipoConta.CORRENTE);
									continue;
								case 2:
									// »MENU CRIAÇÃO DE CONTA - 2 - CONTA POUPANÇA
									new ContaBO(contaCorrenteBO.getCliente(), TipoConta.POUPANCA);
									continue;
								case 0:
									// »MENU LOGADO - 0 - VOLTAR AO MENU ANTERIOR
									break;
								default:
									Util.writeConsole("Opção Inválida!\n");
									continue;
								}

							}
							continue;
						case 0:
							// »MENU LOGADO - 0 - SAIR DA CONTA
							Util.writeConsole("Até mais!");
							i = 0;
							break;
						default:
							continue;
						}

					}

				} else {
					Util.writeConsole("Login ou senha incorretos! Tente novamente");
				}
				i = -1;
				continue;
			case 3:
				// »MENU PRINCIPAL - TRANSFERÊNCIA SEM FAZER LOGIN
				Conta contaDestino = DataBase
						.getContaDB(Util.readConsoleInt("Informe a conta em que deseja depositar:"));
				if (contaDestino != null) {
					if (contaDestino.getNumeroConta() != null) {
						Double valorDepositado = Util.readConsoleDouble("Qual o valor do depósito?");
						ContaBO contaDestinoBO = new ContaBO(contaDestino);
						contaDestinoBO.depositar(contaDestino, valorDepositado, false);
					}
				}
				continue;
			case 0:
				// »MENU PRINCIPAL - 0 - SAIR DO SISTEMA
				break;
			default:
				Util.writeConsole("Opção Inválida");
				continue;
			}

		}

	}

}
