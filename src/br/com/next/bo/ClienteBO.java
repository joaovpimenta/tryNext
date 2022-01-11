package br.com.next.bo;

import br.com.next.bean.Cliente;
import br.com.next.bean.TipoCliente;
import br.com.tryNext.Endereco;

public class ClienteBO {

	public ClienteBO() {

	}

	public Cliente cadastrarCliente(String cpf, String nome, Endereco endereco) {

		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		// cliente.dataNascimento;
		cliente.setTipo(TipoCliente.COMUM);
		cliente.setEndereco(endereco);

		return cliente;
	}

	public boolean validaCPF(String cpf) {

		if (!cpf.matches("[0-9]{11}")) {
			return true;
		} else {
			return false;
		}

	}

}
