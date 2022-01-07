package br.com.next;

import java.util.Date;

public class Cliente {

	private String cpf;
	private String nome;
	// private Date dataNascimento;
	private TipoCliente tipo;
	private Endereco endereco;

	public Cliente(String cpf, String nome, Endereco endereco) {

		this.setCpf(cpf);
		this.setNome(nome);
		// this.dataNascimento;
		this.setTipo(TipoCliente.COMUM);
		this.endereco = endereco;

	}

	// GETTERS E SETTERS

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
