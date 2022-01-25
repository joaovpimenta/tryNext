package br.com.next.bean;

import java.util.Calendar;

import br.com.next.utils.Util;

public class Apolice {
	
	private Integer id;
	private Calendar dataAssinatura;
	private Calendar dataCarencia;
	private Calendar dataValidade;
	private Seguro seguro;

	public Apolice(Integer anosDuracao, TipoSeguro tipoSeguro) {
		this.id = Util.randInt(10000, 99999);
		this.dataAssinatura = Calendar.getInstance();
		this.dataCarencia = getDataAssinatura();
		this.dataCarencia.add(Calendar.DAY_OF_YEAR, 15);
		this.dataValidade = getDataAssinatura();
		this.dataValidade.add(Calendar.YEAR, anosDuracao);
		this.seguro = new Seguro(tipoSeguro);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Calendar dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Calendar getDataCarencia() {
		return dataCarencia;
	}

	public void setDataCarencia(Calendar dataCarencia) {
		this.dataCarencia = dataCarencia;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	
	
}
