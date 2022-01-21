package br.com.next.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.bean.Compras;
import br.com.next.bean.Conta;
import br.com.next.bean.Pix;

public class DataBase {

	private static Map<Integer, Conta> dataBase = new HashMap<Integer, Conta>();

	public static Conta getContaDB(Integer IdConta) {

		Conta conta = DataBase.dataBase.get(IdConta);

		if (conta == null) {
			Util.writeConsole("Conta não encontrada", 44, "-");
			return null;
		}
		return conta;
	}

	public static void setContaDB(Integer numeroConta, Conta conta) {
		
		DataBase.dataBase.put(numeroConta, conta);

	}

	public static List<Conta> returnContasByCpfSenha(String cpf, String senha) {
		
		List<Conta> listaContas = new ArrayList<Conta>();

		for (Map.Entry<Integer, Conta> conta : dataBase.entrySet()) {

			if (conta.getValue().getCliente().getCpf().equals(cpf)
					&& conta.getValue().getCliente().getSenha().equals(senha)) {
				listaContas.add(conta.getValue());
			}
			
		}
		return listaContas;
	}

	public static List<Conta> returnContasByCpf(String cpf) {
		List<Conta> listaContas = new ArrayList<Conta>();

		for (Map.Entry<Integer, Conta> conta : dataBase.entrySet()) {

			if (conta.getValue().getCliente().getCpf().equals(cpf)) {
				listaContas.add(conta.getValue());
			}
		}
		return listaContas;
	}

	public static Conta returnContasByChavePix(String chavePix) {

		for (Map.Entry<Integer, Conta> conta : dataBase.entrySet()) {

			Pix pix = conta.getValue().getPix();
			if (pix != null) {
				if (pix.getValorChave().equals(chavePix)) {
					return conta.getValue();
				}
			}
		}
		System.out.println("Chave Pix inválida");
		return null;
	}

	public static boolean novaCompra(Compras compras, Conta conta) {
		conta.getCartaoCredito().getCompras().add(compras);
		return true;
	}

}
