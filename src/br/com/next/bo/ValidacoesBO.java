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

	public boolean validaEmail(String email) {

		if (!email.matches(
				"(?:[a-z0-9]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validaTelefone(String telefone) {

		if (!telefone.matches("[+]{1}[0-9]{2}[(]{1}[0-9]{2}[)]{1}[0-9]{5}-[0-9]{4}")) {
			return true;
		} else {
			return false;
		}

	}
}
