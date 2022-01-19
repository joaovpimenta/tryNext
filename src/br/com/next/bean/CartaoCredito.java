package br.com.next.bean;

import java.util.Calendar;
import java.util.Date;

public class CartaoCredito extends Cartao {

	private Double limite;
	private Double valorFatura;
	private Date vencimentoFatura;

	public CartaoCredito(String senha, Cliente cliente) {
		super(senha, cliente);
		this.setLimite(cliente);
		this.valorFatura = 0.0;
		this.vencimentoFatura = avancaMes();
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Cliente cliente) { // TODO Adicionar isso ao menu de cartões de crédito
		TipoCliente tipoCliente = cliente.getTipo();
		Double limite = (tipoCliente == TipoCliente.COMUM) ? 3000.0
				: (tipoCliente == TipoCliente.SUPER) ? 8000.0 : 12000.0;
		this.limite = limite;
	}

	public Double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(Double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Date avancaMes() {
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.MONTH, 1);
		Date dataExecucao = calendario.getTime();
		return dataExecucao;
	}

	public Date getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(Date vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}

}
