package br.com.next;

import java.util.HashMap;
import java.util.Map;

import br.com.next.bean.Cliente;
import br.com.next.utils.Menu;
import br.com.tryNext.Endereco;

public class StartProjetoNext {

	public static Map<String, Cliente> mapCliente = new HashMap<String, Cliente>();

	public static void main(String[] args) {
		
		Menu.menu();
		
		//Cliente cliente1 = new Cliente("12345678912", "João Victor", null);
		Endereco endereco = null;
		//Cliente cliente2 = new Cliente("34516457658", "Maria Eduarda", null);
	//	Endereco endereco2 = null;

	//	StartProjetoNext.mapCliente.put("iddqd", cliente1);
	//	StartProjetoNext.mapCliente.put("abaca", cliente2);
		
		Cliente cliente = StartProjetoNext.mapCliente.get("iddqd");
		
		System.out.println(cliente.getNome());
		
	}

}
