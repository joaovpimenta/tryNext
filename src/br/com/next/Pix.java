package br.com.next;

import java.util.Date;

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
