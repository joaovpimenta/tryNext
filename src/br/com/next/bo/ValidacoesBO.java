package br.com.next.bo;

public class ValidacoesBO {

	public boolean validaCPF(String cpf) {

		if (!cpf.matches("[0-9]{11}")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validaLogradouro(String logradouro) {

		if (!logradouro.matches("[a-zA-Z ]*")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validaNumero(String numeroLogradouro) {
		if (!numeroLogradouro.matches("[0-9]*")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaCep(String cep) {
		if (!cep.matches("[0-9]{5}-[0-9]{3}")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validaEstado(String estado) {

		if (!estado.matches("[A-Z ]{2}")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validaSenha(String senha) {

		if (!senha.matches("[0-9]{4}")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validaData(String data) {

		if (!data.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
			return true;
		} else {
			return false;
		}

	}
}
