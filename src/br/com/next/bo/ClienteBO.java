package br.com.next.bo;

import br.com.next.bean.Cliente;
import br.com.next.bean.Endereco;
import br.com.next.bean.TipoCliente;

public class ClienteBO {

	public ClienteBO() {

	}

	public Cliente cadastrarCliente(String cpf, String nome, Endereco endereco, String senha) {

		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		// cliente.dataNascimento;
		cliente.setTipo(TipoCliente.COMUM);
		cliente.setEndereco(endereco);
		cliente.setSenha(senha);

		return cliente;
	}

}
