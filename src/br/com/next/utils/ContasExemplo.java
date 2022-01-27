package br.com.next.utils;

import br.com.next.bean.Cliente;
import br.com.next.bean.Endereco;
import br.com.next.bean.TipoConta;
import br.com.next.bo.ClienteBO;
import br.com.next.bo.ContaBO;

public class ContasExemplo {
	

	
	@SuppressWarnings("unused")
	public static void abreContas() {
		ClienteBO clienteBOJ = new ClienteBO();
		Endereco enderecoJ = new Endereco("Av. Afonso Pena", "3451", "30110-028", "Centro", "Belo Horizonte", "MG");
		Cliente clienteJ = clienteBOJ.cadastrarCliente("12345678912", "João Victor", Util.readConsoleData("16/07/1996"), enderecoJ, "1234");
		ContaBO contaCorrenteBOJ = new ContaBO(clienteJ, TipoConta.CORRENTE);
		ContaBO contaPoupancaBOJ = new ContaBO(clienteJ, TipoConta.POUPANCA);
		
		ClienteBO clienteBOG = new ClienteBO();
		Endereco enderecoG = new Endereco("Av. Afonso Pena", "1449", "30110-028", "Centro", "Belo Horizonte", "MG");
		Cliente clienteG = clienteBOG.cadastrarCliente("12345678978", "Gabriela Simões", Util.readConsoleData("07/09/1998"), enderecoG, "4321");
		ContaBO contaCorrenteBOG = new ContaBO(clienteG, TipoConta.CORRENTE);
		ContaBO contaPoupancaBOG = new ContaBO(clienteG, TipoConta.POUPANCA);

		ClienteBO clienteBOS = new ClienteBO();
		Endereco enderecoS = new Endereco("Av. Afonso Pena", "3451", "30110-028", "Centro", "Belo Horizonte", "MG");
		Cliente clienteS = clienteBOS.cadastrarCliente("12332112332", "Sofia Pimenta", Util.readConsoleData("13/01/2020"), enderecoS, "1234");
		ContaBO contaPoupancaBOS = new ContaBO(clienteS, TipoConta.POUPANCA);
		
	}
	
}
