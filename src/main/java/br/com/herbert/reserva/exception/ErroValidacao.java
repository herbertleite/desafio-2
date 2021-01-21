package br.com.herbert.reserva.exception;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends StandardError {

	private static final long serialVersionUID = 1L;
	private List<MenssagemCampoValidacao> erros = new ArrayList<>();
	
	public ErroValidacao(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public void adicionaErro(String nome, String mensagem) {
		erros.add(new MenssagemCampoValidacao(nome, mensagem));
	}

	public List<MenssagemCampoValidacao> getErros() {
		return erros;
	}

	public void setErros(List<MenssagemCampoValidacao> erros) {
		this.erros = erros;
	}

}