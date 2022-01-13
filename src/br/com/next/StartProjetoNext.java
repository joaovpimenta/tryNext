package br.com.next;

import java.util.HashMap;
import java.util.Map;

import br.com.next.bean.Cliente;
import br.com.next.bean.Endereco;
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

				new ContaBO(clienteBO.cadastrarCliente(cpf, nome, endereco, senha));

				System.out.println("Cadastro Realizado com sucesso!");

				continue;
			case 2:
				System.out.println("Não implementado, reclame aqui: mailto:joaovpiment@gmail.com");
				continue;
			case 3:

				String loginCpf = "-";
				while (validacoesBO.validaCPF(loginCpf)) {
					loginCpf = util.readConsole("Digite o seu CPF: ");
				}

				String loginSenha = "-";
				while (validacoesBO.validaSenha(loginSenha)) {
					loginSenha = util.readConsole("Digite sua senha: ");
				}

				if (DataBase.returnContaByCpfSenha(loginCpf, loginSenha) != null) {
					System.out.println("Sucesso");
				} else {
					System.out.println("Falha");
				}
				continue;
			case 0:
				continue;
			default:
				System.out.println("Opção Inválida");
				break;
			}

		}

	}

}
