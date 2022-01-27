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
import br.com.next.bean.TipoSeguro;
import br.com.next.bo.CartaoCreditoBO;
import br.com.next.bo.CartaoDebitoBO;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;
import br.com.next.bo.ValidacoesBO;
import br.com.next.utils.ContasExemplo;
import br.com.next.utils.DataBase;
import br.com.next.utils.Menu;
import br.com.next.utils.Util;

public class StartProjetoNext {

	public static Map<String, Cliente> mapCliente = new HashMap<String, Cliente>();
	private static ClienteBO clienteBOJ = new ClienteBO();
	static ContaBO contaCorrenteBO = null;
	static ContaBO contaPoupancaBO = null;
	static ContaBO contaEmUso;
	static String loginCpf = "-";
	static int i = -1;

	public static void main(String[] args) {

		ValidacoesBO validacoesBO = new ValidacoesBO();
		DataBase.setListaSeguros();
		ContasExemplo.abreContas();

		Util.loading(44);

		while (i != 0) {

			Menu.menuPrincipal();
			i = Util.readConsoleInt();

			switch (i) {
			case 1: // »MENU I - PRINCIPAL - 1 - CRIAR CONTA
				i = menuCriarCliente(validacoesBO);
				continue;
			case 2: // »MENU I - PRINCIPAL - 2 - LOGIN

				List<Conta> listaContas = menuLogin(validacoesBO);

				if (contaCorrenteBO != null || contaPoupancaBO != null) {

					while (i != 0) {

						Menu.menuLogado();
						i = Util.readConsoleInt();

						switch (i) {
						case 1: // »MENU II - LOGADO - 1 - REALIZAR TRANSAÇÕES

							while (i != 0) {
								Menu.menuTransacoes();
								i = Util.readConsoleInt();

								switch (i) {
								case 1:
									// »MENU III - TRANSAÇÕES - 1 - REALIZAR DEPÓSITO
									Conta contaDestino = DataBase.getContaDB(
											Util.readConsoleInt("Informe a conta em que deseja depositar:"));
									if (contaDestino != null) {
										if (contaDestino.getNumeroConta() != null) {
											Double valorDepositado = Util
													.readConsoleDouble("Qual o valor do depósito?");
											contaCorrenteBO.depositar(contaDestino, valorDepositado, true);
										}
									}
									mostrarSaldo();
									continue;
								case 2:
									// »MENU III - TRANSAÇÕES - 2 - REALIZAR SAQUE
									definirContaDaOperacao();

									Double valorSaque = Util.readConsoleDouble("Qual o valor do saque?");
									contaEmUso.sacar(valorSaque);

									continue;
								case 3:
									// »MENU III - TRANSAÇÕES - 3 - REALIZAR TRANSFERÊNCIA
									contaDestino = DataBase.getContaDB(
											Util.readConsoleInt("Informe a conta para qual deseja transferir:"));
									if (contaDestino != null) {

										if (contaDestino.getNumeroConta() != null) {
											Double valor = Util.readConsoleDouble("Qual o valor da transferência?");

											definirContaDaOperacao();
											contaEmUso.transferir(contaDestino, valor);

										}
									}
									continue;
								case 0:
									// »MENU III - TRANSAÇÕES - 0 - VOLTAR AO MENU ANTERIOR
									break;

								default:
									Util.writeConsoleError("Opção Inválida!", 44, "<");
									continue;
								}
							}
							i = -1;
							continue;
						case 2:
							// »MENU II - LOGADO - 2 - CONSULTAR SALDO
							mostrarSaldo();
							continue;
						case 3:
							// »MENU II - LOGADO - 3 - ÁREA PIX
							Menu.menuPix();
							i = Util.readConsoleInt();
							switch (i) {
							case 1:
								// »MENU PIX - 3 - CONSULTAR CHAVE
								definirContaDaOperacao();
								if (contaEmUso != null) {
									try {
										Util.writeConsole(contaEmUso.getChavePix(), 44, "-");
									} catch (Exception e) {
										Util.writeConsole("Nenhuma chave cadastrada para essa conta", 44, "-");
									}
								}

								continue;
							case 2:
								// »MENU PIX - 3 - CADASTRAR PIX
								cadastroPix(validacoesBO);
								continue;
							case 3:
								// »MENU PIX - 3 - TRANSFERIR VIA PIX

								String chavePix = Util.readConsole("Informe a chave Pix:");
								Double valorPix = Util.readConsoleDouble("Qual o valor da transferência?");

								Conta contaDestino = DataBase.returnContasByChavePix(chavePix);

								if (contaDestino == null) {
									continue;
								}

								contaEmUso.transferirViaPix(contaDestino, valorPix);

								continue;
							case 0:
								// »MENU PIX - 0 - VOLTAR AO MENU ANTERIOR
								break;

							default:
								Util.writeConsoleError("Opção Inválida!", 44, "<");
								continue;
							}
							i = -1;
							continue;
						case 4: // »MENU II - LOGADO - 4 - ÁREA DE CARTÕES
							Menu.menuCartoes();
							i = Util.readConsoleInt();

							if (i == 0) {
								break;
							}

							CartaoCredito cartaoCreditoEmUso = null;
							CartaoDebito cartaoDebitoEmUso = null;

							switch (i) {
							case 1: // »MENU ÁREA DE CARTÕES - 1 - CARTÃO DE CRÉDITO

								definirContaDaOperacao();

								cartaoCreditoEmUso = contaEmUso.getConta().getCartaoCredito();

								while (i != 0) {

									if (cartaoCreditoEmUso == null) {
										Util.writeConsoleError("Você não tem um cartão de crédito", 44, "-");
										i = -1;
									} else {
										Menu.menuCartaoCredito();
										i = Util.readConsoleInt();
									}

									switch (i) {
									case 1: // »MENU CARTÃO DE CRÉDITO - 1 - COMPRA COM CRÉDITO

										String nomeProduto = Util.readConsole("Qual nome do produto?");
										String descricao = Util.readConsole("Descrição da compra");
										Double valorCompra = Util.readConsoleDouble("Preço do produto?");

										String dataDaCompra = "";
										while (validacoesBO.validaData(dataDaCompra)) {
											dataDaCompra = Util.readConsole("Qual a data da compra?");
										}
										Date dataCompra = Util.readConsoleData(dataDaCompra);

										CartaoCreditoBO cartaoCreditoBO = new CartaoCreditoBO(cartaoCreditoEmUso);

										String mensagem = (cartaoCreditoBO.novaCompra(dataCompra, valorCompra,
												descricao, nomeProduto, contaEmUso)) ? "Compra efetuada com sucesso!"
														: "Compra não foi efetuada!";
										Util.writeConsole(mensagem, 44, "-");

										continue;
									case 2:// »MENU CARTÃO DE CRÉDITO - 2 - CONSULTAR FATURA
										Util.writeConsole(cartaoCreditoEmUso.gerarFatura(), 44, "<");

										continue;
									case 3:// »MENU CARTÃO DE CRÉDITO - 3 - SOLICITAR AUMENTO DO LIMITE
										Double limiteAntigo = cartaoCreditoEmUso.getLimite();
										cartaoCreditoEmUso.setLimiteInicio(contaEmUso.getCliente());
										Double limiteAtual = cartaoCreditoEmUso.getLimite();

										mensagem = (limiteAtual != limiteAntigo)
												? "Solicitação de limite recebida. Seu novo limite é: " + limiteAtual
												: "Infelizmente não foi possivel aumentar se limite. Seu limite atual é:"
														+ limiteAntigo;
										Util.writeConsole(mensagem, 44, "<");

										continue;
									case 4:// »MENU CARTÃO DE CRÉDITO - 4 - BLOQUEAR CARTÃO
										cartaoCreditoEmUso.setIsAtivo();
										Boolean isAtivo = cartaoCreditoEmUso.getIsAtivo();
										mensagem = (isAtivo) ? "Status do cartao: Ativo" : "Status do cartao: Inativo";
										Util.writeConsole(mensagem, 44, "<");

										continue;
									case 5:// »MENU CARTÃO DE CRÉDITO - 5 - CONTRATAR SEGURO
										Menu.menuSeguros();
										i = Util.readConsoleInt();

										if (i == 4) {
											try {
												Util.writeConsole(
														cartaoCreditoEmUso.getApolice().getSeguro().getRegra(), 44,
														"<");
											} catch (Exception e) {
												Util.writeConsoleError("Opção Inválida: Você não possui Apólices", 44,
														"-");
											}

											continue;
										}

										TipoSeguro tipoSeguro = (i == 1) ? TipoSeguro.MORTE
												: (i == 2) ? TipoSeguro.INVALIDEZ : TipoSeguro.DESEMPREGO;
										Integer anosDuracao = Util
												.readConsoleInt("Por quantos anos deseja contratar o seguro?");
										cartaoCreditoEmUso.setApolice(anosDuracao, tipoSeguro);

										continue;
									case 0:
										// »MENU CARTÃO DE CRÉDITO - 0 - VOLTAR AO MENU ANTERIOR
										break;
									default:
										Util.writeConsoleError("Opção Inválida!", 44, "<");
										continue;
									}

								}
								continue;
							case 2: // »MENU ÁREA DE CARTÕES - 2 - CARTÃO DE DÉBITO

								definirContaDaOperacao();

								cartaoDebitoEmUso = contaEmUso.getConta().getCartaoDebito();

								if (cartaoDebitoEmUso == null) {
									Util.writeConsole("Você não tem um cartão de débito", 44, "-");
									i = -1;
								} else {
									Menu.menuCartaoDebito();
									i = Util.readConsoleInt();
								}

								if (i == 0) {
									break;
								}

								switch (i) {
								case 1: // »MENU CARTÃO DE DÉBITO - 1 - COMPRA COM DÉBITO

									String nomeProduto = Util.readConsole("Qual nome do produto?");
									String descricao = Util.readConsole("Descrição da compra");
									Double valorCompra = Util.readConsoleDouble("Preço do produto?");

									String dataDaCompra = "";
									while (validacoesBO.validaData(dataDaCompra)) {
										dataDaCompra = Util.readConsole("Qual a data da compra?");
									}
									Date dataCompra = Util.readConsoleData(dataDaCompra);

									CartaoDebitoBO cartaoDebitoBO = new CartaoDebitoBO(cartaoDebitoEmUso);

									String mensagem = (cartaoDebitoBO.novaCompra(dataCompra, valorCompra, descricao,
											nomeProduto, contaEmUso)) ? "Compra efetuada com sucesso!"
													: "Compra não foi efetuada!";
									Util.writeConsole(mensagem, 44, "-");

									continue;
								case 2: // »MENU CARTÃO DE DÉBITO - 2 - CONSULTAR EXTRATO
									Util.writeConsole(cartaoDebitoEmUso.gerarFatura(), 44, "<");
									continue;
								case 3: // »MENU CARTÃO DE DÉBITO - 3 - ALTERAR LIMITE POR TRANSAÇÃO
									Double novoLimiteTransacao = Util
											.readConsoleDouble("Insira o limite por transação desejado:");
									cartaoDebitoEmUso.setLimiteTransacao(novoLimiteTransacao);

									mensagem = "Solicitação de alteração de limite recebida. Seu novo limite é: R$"
											+ cartaoDebitoEmUso.getLimiteTransacao();
									Util.writeConsole(mensagem, 44, "<");

									continue;
								case 4: // »MENU CARTÃO DE DÉBITO - 4 - BLOQUEAR CARTÃO
									cartaoDebitoEmUso.setIsAtivo();
									mensagem = (cartaoDebitoEmUso.getIsAtivo()) ? "Status do cartao: Ativo"
											: "Status do cartao: Inativo";
									Util.writeConsole(mensagem, 44, "<");

									continue;
								case 0:
									// »MENU CARTÃO DE CRÉDITO - 0 - VOLTAR AO MENU ANTERIOR
									break;
								default:
									Util.writeConsoleError("Opção Inválida!", 44, "<");
									continue;
								}

								continue;
							case 3: // »MENU ÁREA DE CARTÕES - 3 - SOLICITAR CARTÃO

								definirContaDaOperacao();

								Cliente clienteDoCartao = contaEmUso.getCliente();

								Menu.menuSolicitarCartao();
								i = Util.readConsoleInt();

								if (i == 0) {
									break;
								}

								switch (i) {
								case 1: // »MENU SOLICITAR CARTÃO - 1 - CARTÃO DE CRÉDITO

									cartaoCreditoEmUso = contaEmUso.getConta().getCartaoCredito();

									if (cartaoCreditoEmUso != null) {
										Util.writeConsole("Você já tem um cartão de Crédito!", 44, "<");
										continue;
									} else {
										String senhaCartao = "-";
										while (validacoesBO.validaSenha(senhaCartao)) {
											senhaCartao = Util.readConsole("Digite uma senha de 4 digitos:");
										}
										Menu.menuDiaVencimento();
										i = Util.readConsoleInt("Qual o melhor dia para o vencimento?");

										int diaVencimento;
										switch (i) {
										case 1:
											diaVencimento = 1;
											break;
										case 2:
											diaVencimento = 10;
											break;
										case 3:
											diaVencimento = 15;
											break;
										case 4:
											diaVencimento = 25;
											break;
										default:
											continue;
										}

										CartaoCreditoBO cartaoCreditoBO = new CartaoCreditoBO(clienteDoCartao,
												senhaCartao, diaVencimento);
										cartaoCreditoEmUso = cartaoCreditoBO.getCartaoCredito();
										contaEmUso.adicionarCartaoCredito(cartaoCreditoEmUso);
									}

									continue;
								case 2: // »MENU SOLICITAR CARTÃO - 2 - CARTÃO DE DÉBITO

									CartaoDebito CartaoDebitoExistente = contaEmUso.getConta().getCartaoDebito();

									if (CartaoDebitoExistente != null) {
										Util.writeConsole("Você já tem um cartão de Débito!", 44, "<");
										continue;
									} else {
										String senhaCartao = "-";
										while (validacoesBO.validaSenha(senhaCartao)) {
											senhaCartao = Util.readConsole("Digite uma senha de 4 digitos:");

											Double limiteTransacao = Util
													.readConsoleDouble("Defina o limite por transação?");

											CartaoDebitoBO cartaoDebitoBO = new CartaoDebitoBO(clienteDoCartao,
													senhaCartao, limiteTransacao);
											cartaoDebitoEmUso = cartaoDebitoBO.getCartaoDebito();
											contaEmUso.adicionarCartaoDebito(cartaoDebitoEmUso);

										}
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
								Util.writeConsoleError("Opção Inválida!", 44, "<");
								continue;
							}

							continue;
						case 5:
							// »MENU II - LOGADO - 5 - CRIAR OUTRA CONTA
							i = -1;

							while (i != 0) {
								Menu.menuCriacaoConta();
								i = Util.readConsoleInt();

								listaContas = DataBase.returnContasByCpf(loginCpf);

								for (Conta contaIteradora : listaContas) {

									if (i == contaIteradora.getTipoConta().getId()) {
										String mensagem = "Essa conta já existe! O número é: "
												+ contaIteradora.getNumeroConta();
										Util.writeConsole(mensagem, 44, "<");

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
									// »MENU CRIAÇÃO DE CONTA - 0 - VOLTAR AO MENU ANTERIOR
									break;
								default:
									Util.writeConsoleError("Opção Inválida!", 44, "<");
									continue;
								}

							}
							continue;
						case 0:
							// »MENU II - LOGADO - 0 - SAIR DA CONTA
							Util.writeConsole("Até mais!", 44, "<");
							i = 0;
							break;
						default:
							continue;
						}

					}

				} else {
					Util.writeConsole("Login ou senha incorretos! Tente novamente", 44, "<");
				}
				i = -1;
				continue;
			case 3:
				// »MENU I - PRINCIPAL - TRANSFERÊNCIA SEM FAZER LOGIN
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
				// »MENU I - PRINCIPAL - 0 - SAIR DO SISTEMA
				break;
			default:
				Util.writeConsole("Opção Inválida", 44, "<");
				continue;
			}

		}

	}

	private static void mostrarSaldo() {
		if (contaCorrenteBO != null && contaPoupancaBO != null) {

			Util.writeConsole(contaCorrenteBO.consultaSaldo(), 44, "<");
			Util.writeConsole(contaPoupancaBO.consultaSaldo(), 44, "<");

		} else if (contaCorrenteBO != null) {
			Util.writeConsole(contaCorrenteBO.consultaSaldo(), 44, "<");
		} else {
			Util.writeConsole(contaPoupancaBO.consultaSaldo(), 44, "<");
		}
	}

	private static void definirContaDaOperacao() {

		if (contaCorrenteBO != null && contaPoupancaBO != null) {
			int escolhaConta = Util
					.readConsoleInt("De qual conta deseja realizar operação? (1 - CORRENTE, 2 - POUPANÇA)");
			if (escolhaConta == 1) {
				contaEmUso = contaCorrenteBO;
			} else {
				contaEmUso = contaPoupancaBO;
			}
		} else if (contaCorrenteBO != null) {
			contaEmUso = contaCorrenteBO;
		} else {
			contaEmUso = contaPoupancaBO;
		}
	}

	private static void cadastroPix(ValidacoesBO validacoesBO) {
		definirContaDaOperacao();
		Pix pix = new Pix();
		pix.setIsAtivado(true);

		Menu.menuCadastroPix();
		i = Util.readConsoleInt();

		switch (i) {
		case 1: // »MENU CADASTRO CHAVE - 1 - CPF
			String chaveCpf = "-";
			while (validacoesBO.validaCPF(chaveCpf)) {
				chaveCpf = Util.readConsole("Digite o número de CPF:");
			}
			pix.setTipoChavePix(TipoChavePix.CPF);
			pix.setValorChave(chaveCpf);
			contaEmUso.adicionarPix(pix);
			return;
		case 2: // »MENU CADASTRO CHAVE - 2 - EMAIL
			String chaveEmail = "-";
			while (validacoesBO.validaEmail(chaveEmail)) {
				chaveEmail = Util.readConsole("Digite o endereço de Email:");
			}
			pix.setTipoChavePix(TipoChavePix.EMAIL);
			pix.setValorChave(chaveEmail);
			contaEmUso.adicionarPix(pix);
			return;
		case 3: // »MENU CADASTRO CHAVE - 3 - TELEFONE
			String chaveTelefone = "-";
			while (validacoesBO.validaTelefone(chaveTelefone)) {
				chaveTelefone = Util.readConsole("Digite o número de Telefone: (Ex: +55(31)99999-9999 ");
			}
			pix.setTipoChavePix(TipoChavePix.TELEFONE);
			pix.setValorChave(chaveTelefone);
			contaEmUso.adicionarPix(pix);
			return;
		case 4: // »MENU CADASTRO CHAVE - 4 - ALEATÓRIO
			String chaveAleatoria = UUID.randomUUID().toString();
			pix.setTipoChavePix(TipoChavePix.ALEATORIO);
			pix.setValorChave(chaveAleatoria);
			contaEmUso.adicionarPix(pix);
			return;
		case 0:
			// »MENU CADASTRO CHAVE - 0 - VOLTAR AO MENU ANTERIOR
			break;
		default:
			Util.writeConsoleError("Opção Inválida!", 44, "<");
			return;
		}

		i = -1;
	}

	private static List<Conta> menuLogin(ValidacoesBO validacoesBO) {

		i = -1;

		while (validacoesBO.validaCPF(loginCpf)) {
			loginCpf = Util.readConsole("Digite o seu CPF:");
		}

		String loginSenha = "-";
		while (validacoesBO.validaSenha(loginSenha)) {
			loginSenha = Util.readConsole("Digite sua senha:");
		}

		List<Conta> listaContas = DataBase.returnContasByCpfSenha(loginCpf, loginSenha);

		for (Conta conta : listaContas) {

			if (TipoConta.CORRENTE.name().equals(conta.getTipoConta().toString())) {
				contaCorrenteBO = new ContaBO(conta);
			} else {
				contaPoupancaBO = new ContaBO(conta);
			}
		}
		return listaContas;
	}

	private static int menuCriarCliente(ValidacoesBO validacoesBO) {
		int i;

		ClienteBO clienteBO = new ClienteBO();

		String cpf = "-";
		while (validacoesBO.validaCPF(cpf)) {
			cpf = Util.readConsole("Digite o número de CPF:");
		}

		String nome = Util.readConsole("Digite o seu nome completo:");

		String data = "";
		while (validacoesBO.validaData(data)) {
			data = Util.readConsole("Informe a data de nascimento (dd/mm/yyyy):");
		}
		Date dataNascimento = Util.readConsoleData(data);

		String logradouro = "-";
		while (validacoesBO.validaLogradouro(logradouro)) {
			logradouro = Util.readConsole("Informe o seu Logradouro:");
		}

		String numeroLogradouro = "-";
		while (validacoesBO.validaNumero(numeroLogradouro)) {
			numeroLogradouro = Util.readConsole("Informe o número do Logradouro:");
		}

		String cep = "-";
		while (validacoesBO.validaCep(cep)) {
			cep = Util.readConsole("Informe seu CEP:");
		}

		String bairro = Util.readConsole("Informe o Bairro:");
		String cidade = Util.readConsole("Informe a Cidade:");

		String estado = "-";
		while (validacoesBO.validaEstado(estado)) {
			estado = Util.readConsole("Informe o Estado:").toUpperCase();
		}

		String senha = "-";
		while (validacoesBO.validaSenha(senha)) {
			senha = Util.readConsole("Cadastre uma senha:");
		}

		Endereco endereco = new Endereco(logradouro, numeroLogradouro, cep, bairro, cidade, estado);

		Cliente cliente = clienteBO.cadastrarCliente(cpf, nome, dataNascimento, endereco, senha);

		i = -1;

		while (i != 0) {
			Menu.menuCriacaoConta();
			i = Util.readConsoleInt();

			List<Conta> listaContas = DataBase.returnContasByCpf(cpf);

			for (Conta contaIteradora : listaContas) {

				if (i == contaIteradora.getTipoConta().getId()) {
					String mensagem = "Essa conta já existe! O número é: " + contaIteradora.getNumeroConta();
					Util.writeConsole(mensagem, 44, "<");
					i = -1;
					break;
				}

			}

			menuCriarConta(cliente);

		}

		i = -1;
		return i;
	}

	private static void menuCriarConta(Cliente cliente) {
		switch (i) {
		case 1:
			// »MENU CRIAÇÃO DE CONTA - 1 - CONTA CORRENTE
			new ContaBO(cliente, TipoConta.CORRENTE);
			return;
		case 2:
			// »MENU CRIAÇÃO DE CONTA - 2 - CONTA POUPANÇA
			new ContaBO(cliente, TipoConta.POUPANCA);
			return;
		case 0:
			// »MENU CRIAÇÃO DE CONTA - 0 - VOLTAR AO MENU ANTERIOR
			break;
		default:
			Util.writeConsoleError("Opção Inválida!", 44, "<");
			return;
		}
	}

}
