package br.com.next;

import br.com.util.Util;

public class Main {

	public static void main(String[] args) {
		
		Util util = new Util();
		
		int i;
		
		i = util.readConsoleInt("Seja bem vindo, o que deseja fazer?\n"+
		"1 - Cadastrar Novo Cliente");
		
		switch (i) {
		case 1:
			Cliente cliente = new Cliente();
			
			cliente.cadastrarDados();
			
			break;
		case 2:
			System.out.println("teste 2");
			break;
		default:
			break;
		}
	}

}
