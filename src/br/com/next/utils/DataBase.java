package br.com.next.utils;

import java.util.HashMap;
import java.util.Map;

import br.com.next.bean.Conta;

public class DataBase {

	private static Map<String, Conta> dataBase = new HashMap<String, Conta>();

	public static Conta getContaById(String IdConta) {

		Conta conta = DataBase.dataBase.get(IdConta);

		if (conta == null) {
			System.out.println("Conta não encontrada");
			return null;
		}
		return conta;
	}
	public static void insertConta(String numeroConta, Conta conta) {
		
		DataBase.dataBase.put(numeroConta, conta);
		
	}

}
