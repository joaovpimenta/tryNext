package br.com.tryNext;

import java.util.Date;

import br.com.next.bean.Conta;

public class Pix {
	
	public Integer id;
	public TipoChavePix tipoChave;
	public Double valor;
	public Date data;
	public String conteudoChave;
	public Boolean isAtivado;
	public Conta conta;
	
	public void AtivarChave(TipoChavePix tipochave, String conteudoChave, Boolean isAtivado) {
		
	}
}
