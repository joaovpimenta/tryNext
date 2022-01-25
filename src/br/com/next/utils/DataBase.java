package br.com.next.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.next.bean.Compras;
import br.com.next.bean.Conta;
import br.com.next.bean.Pix;
import br.com.next.bean.TipoSeguro;

public class DataBase {

	private static Map<Integer, Conta> dataBase = new HashMap<Integer, Conta>();

	private static Map<TipoSeguro, String> listaSeguros = new HashMap<TipoSeguro, String>();

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
	
	public static void setListaSeguros() {

		listaSeguros.put(TipoSeguro.MORTE,
				"i. Indenização por despesas médico-hospitalares, ou por perda parcial ou total do funcionamento dos membros ou órgãos;"
						+ "ii. Reembolso de custos em diagnóstico de doenças graves, como infarto, acidente vascular cerebral e câncer;"
						+ "iii. Assistência funeral, para você e a sua família."
						+ "iv. O valor do seguro individual é de R$36,00 ao ano.");
		listaSeguros.put(TipoSeguro.INVALIDEZ,
				"i. Invalidez parcial: ocorre quando há uma perda parcial das funções. Por exemplo, uma pessoa que sofre um acidente e perda a visão em um só dos olhos."
						+ "ii. Invalidez total: ocorre quando há uma perda total das funções. Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um acidente e perde a visão em ambos os olhos."
						+ "iii. O valor do seguro individual é de R$26,00 ao ano.");
		listaSeguros.put(TipoSeguro.DESEMPREGO,
				"i. Necessário trabalhar com registro CLT, com o tempo mínimo de estabilidade de 12 meses."
						+ "ii. Válido apenas para desligamento involuntários e sem justa causa."
						+ "iii. Não é valido em caso de demissão em massa (10% ou mais de demissões simultâneas) ou falência/encerramento das atividades."
						+ "iv. O valor do seguro individual é de R$16,00 ao ano.");
	}
	
	public static String getListaSeguros(TipoSeguro tipoSeguro) {
		
		String regras = listaSeguros.get(tipoSeguro);
		
		return regras;
	}

}
