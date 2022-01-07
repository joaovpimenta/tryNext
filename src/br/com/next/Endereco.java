package br.com.next;

public class Endereco {

	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;

	public boolean buscarEndereço(String cep) {
		return true;

	}

	public Endereco(String logradouro, String numero, String cep, String bairro, String cidade, String estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

}
