package br.com.next.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.bean.Conta;

public class DataBase {

	private static Map<String, Conta> dataBase = new HashMap<String, Conta>();

	public static Conta getContaDB(String IdConta) {

		Conta conta = DataBase.dataBase.get(IdConta);

		if (conta == null) {
			System.out.println("Conta não encontrada");
			return null;
		}
		return conta;
	}

	public static void setContaDB(String numeroConta, Conta conta) {

		DataBase.dataBase.put(numeroConta, conta);

	}

	public static List<Conta> returnContasByCpfSenha(String cpf, String senha) {
		List<Conta> listaContas = new ArrayList<Conta>();

		for (Map.Entry<String, Conta> conta : dataBase.entrySet()) {

			if (conta.getValue().getCliente().getCpf().equals(cpf)
					&& conta.getValue().getCliente().getSenha().equals(senha)) {
				listaContas.add(conta.getValue());

				return listaContas;
			}
		}
		return null;
	}

	public static List<Conta> returnContasByCpf(String cpf) {
		List<Conta> listaContas = new ArrayList<Conta>();

		for (Map.Entry<String, Conta> conta : dataBase.entrySet()) {

			if (conta.getValue().getCliente().getCpf().equals(cpf)) {
				listaContas.add(conta.getValue());
			}
		}

		return listaContas;

	}

}
