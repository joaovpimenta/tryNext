package br.com.next.bean;

import br.com.next.utils.DataBase;
import br.com.next.utils.Util;

public class Seguro {

	private Integer id;
	private TipoSeguro tipoSeguro;
	private String regras;
	
	
	
	public Seguro(TipoSeguro tipoSeguro) {
		this.id = Util.randInt(10000, 99999);
		this.tipoSeguro = tipoSeguro;
		this.regras = returnRegra(tipoSeguro);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public String getRegra() {
		return regras;
	}
	public void setRegra(String regras) {
		this.regras = regras;
	}
	public String returnRegra(TipoSeguro tipoSeguro) {
		String regra = DataBase.getListaSeguros(tipoSeguro);
		return regra;
	}
	
}
