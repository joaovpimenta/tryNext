package br.com.next.bean;

import java.util.Calendar;
import java.util.Date;

public class CartaoCredito extends Cartao {

	private Double limite;
	private Double valorFatura;
	private Date vencimentoFatura;
	private Apolice apolice;

	public CartaoCredito(Cliente cliente, String senha, Integer diaVencimento) {
		super(senha, cliente);
		this.setLimiteInicio(cliente);
		this.valorFatura = 0.0;
		this.vencimentoFatura = avancaMes(diaVencimento);
	}

	public Double getLimite() {
		return limite;
	}
	
	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public void setLimiteInicio(Cliente cliente) {
		TipoCliente tipoCliente = cliente.getTipo();
		Double limite = (tipoCliente == TipoCliente.COMUM) ? 3000.0
				: (tipoCliente == TipoCliente.SUPER) ? 8000.0 : 12000.0;
		this.limite = limite;
	}

	public Double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(Double valorFatura) {
		this.valorFatura += valorFatura;
	}

	public Date avancaMes(Integer diaVencimento) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.DAY_OF_MONTH, diaVencimento);
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

	public void novaCompra(Compras compras) {
		this.valorFatura += compras.getValor();
		this.compras.add(compras);
		
	}
	
	public Apolice getApolice() {
		return apolice;
	}

	public void setApolice(Integer anosDuracao, TipoSeguro tipoSeguro) {
		this.apolice = new Apolice(anosDuracao, tipoSeguro);
	}

}
