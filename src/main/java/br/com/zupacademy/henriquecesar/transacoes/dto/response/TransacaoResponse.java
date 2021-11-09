package br.com.zupacademy.henriquecesar.transacoes.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;

public class TransacaoResponse {
	
	private String id;
	private BigDecimal valor;
	private EstabelecimentoResponse estabelecimento;
	private CartaoResponse cartao;
	private LocalDateTime efetivadaEm;

	public TransacaoResponse() {
	}

	public TransacaoResponse(Transacao transacao) {
		this.id = transacao.getId();
		this.valor = transacao.getValor();
		this.estabelecimento = new EstabelecimentoResponse(transacao.getEstabelecimento());
		this.cartao = new CartaoResponse(transacao.getCartao());
		this.efetivadaEm = transacao.getDataEfetivacao();
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public EstabelecimentoResponse getEstabelecimento() {
		return estabelecimento;
	}

	public CartaoResponse getCartao() {
		return cartao;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

}
