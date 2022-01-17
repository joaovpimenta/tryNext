package br.com.next.bean;

public class Pix {

	public TipoChavePix tipoChavePix;
	// public Double valor;
	// public Date data;
	public String valorChave;
	public Boolean isAtivado;
	// public Conta conta;

	public void AtivarChave(TipoChavePix tipochave, String valorChave, Boolean isAtivado) {

	}

	public TipoChavePix getTipoChavePix() {
		return tipoChavePix;
	}

	public void setTipoChavePix(TipoChavePix tipoChavePix) {
		this.tipoChavePix = tipoChavePix;
	}

	public String getValorChave() {
		return valorChave;
	}

	public void setValorChave(String valorChave) {
		this.valorChave = valorChave;
	}

	public Boolean getIsAtivado() {
		return isAtivado;
	}

	public void setIsAtivado(Boolean isAtivado) {
		this.isAtivado = isAtivado;
	}

}
