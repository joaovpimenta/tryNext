package br.com.next.bean;

public class Cliente {

	private String cpf;
	private String nome;
	// private Date dataNascimento;
	private TipoCliente tipo;
	private Endereco endereco;
	private String senha;

	public Cliente() {

		this.setCpf(cpf);
		this.setNome(nome);
		// this.dataNascimento;
		this.setTipo(TipoCliente.COMUM);
		this.setEndereco(endereco);
		this.setSenha(senha);

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
