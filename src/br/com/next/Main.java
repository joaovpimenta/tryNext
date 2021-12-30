package br.com.next;

import br.com.util.Util;

public class Main {

	public static void main(String[] args) {
		
		Util util = new Util();
		Cliente cliente = new Cliente();
		Conta conta = new Conta();
		
		int i;
		
		i = util.readConsoleInt("Seja bem vindo, o que deseja fazer?\n"+
		"1 - Cadastrar Novo Cliente\n"+
		"2 - Cadastrar Conta");
		
		switch (i) {
		case 1:
			
			cliente.cadastrarDados();
			
			break;
		case 2:
			
			conta.cadastrarConta(cliente);
			
			break;
		default: util.writeConsole("Opção Inválida!");
			break;
		}
	}

}
