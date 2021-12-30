package br.com.next;

import br.com.util.Util;

public class Cliente {

	private String cpf;
	private String nome;
	private TipoCliente tipo;

	public void cadastrarDados() {

		Util util = new Util();

		do {
			this.cpf = util.readConsole("Informe seu CPF: ");
		} while (!this.cpf.matches("[0-9]{11}"));

		this.setNome(util.readConsole("Informe seu Nome Completo: "));
		this.tipo = TipoCliente.COMUM;
		util.writeConsole("Cadastro realizado com sucesso!\n");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
