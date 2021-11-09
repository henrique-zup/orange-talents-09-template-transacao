package br.com.zupacademy.henriquecesar.transacoes.dto.response;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Cartao;


public class CartaoResponse {

	private String id;

	@Deprecated
	public CartaoResponse() {
	}

	public CartaoResponse(Cartao cartao) {
		this.id = cartao.getId();
	}

	public String getId() {
		return id;
	}
}
