package br.com.next;

import br.com.util.Util;

public class Cliente {
	
	private String cpf;
	private String nome;
	private TipoCliente tipo;
	
	public void cadastrarDados() {
		
		Util util = new Util();
		
		
		do { this.cpf = util.readConsole("Informe seu CPF: ");
			
		} while (this.cpf.length() != 11 || !this.cpf.matches("[0-9]*"));
		
		
		this.nome = util.readConsole("Informe seu Nome Completo: ");
		this.tipo = TipoCliente.COMUM;
		
	}
	
	
}
