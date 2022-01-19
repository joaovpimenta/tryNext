package br.com.next.bo;

import java.util.Date;

import br.com.next.bean.Cliente;
import br.com.next.bean.Endereco;
import br.com.next.bean.TipoCliente;
import br.com.next.utils.Util;

public class ClienteBO {

	public ClienteBO() {

	}

	public Cliente cadastrarCliente(String cpf, String nome, Date dataNascimento, Endereco endereco, String senha) {
		Util.loading();
		
		Cliente cliente = new Cliente();
		cliente.setCpf(cpf);
		cliente.setNome(nome);
		cliente.setDataNascimento(dataNascimento);
		cliente.setTipo(TipoCliente.COMUM);
		cliente.setEndereco(endereco);
		cliente.setSenha(senha);

		return cliente;
	}

}
