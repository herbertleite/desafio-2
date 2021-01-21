package br.com.herbert.reserva.exception;

import java.io.Serializable;

public class MenssagemCampoValidacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String mensagem;
	
	public MenssagemCampoValidacao() {
	}

	/**
	 * @param nome
	 * @param mensagem
	 */
	public MenssagemCampoValidacao(String nome, String mensagem) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}