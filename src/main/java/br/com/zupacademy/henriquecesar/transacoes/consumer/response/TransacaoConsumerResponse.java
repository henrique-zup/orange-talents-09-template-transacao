package br.com.zupacademy.henriquecesar.transacoes.consumer.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;
import br.com.zupacademy.henriquecesar.transacoes.repository.EstabelecimentoRepository;

public class TransacaoConsumerResponse {
	
	private String id;
	private BigDecimal valor;
	private EstabelecimentoConsumerResponse estabelecimento;
	private CartaoConsumerResponse cartao;
	private LocalDateTime efetivadaEm;

	public TransacaoConsumerResponse() {
	}

	public TransacaoConsumerResponse(String id, BigDecimal valor, EstabelecimentoConsumerResponse estabelecimento,
			CartaoConsumerResponse cartao, LocalDateTime efetivadaEm) {
		this.id = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.efetivadaEm = efetivadaEm;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public EstabelecimentoConsumerResponse getEstabelecimento() {
		return estabelecimento;
	}

	public CartaoConsumerResponse getCartao() {
		return cartao;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

	@Override
	public String toString() {
		return "TransacaoConsumerResponse [id=" + id + ", valor=" + valor + ", estabelecimento=" + estabelecimento
				+ ", cartao=" + cartao + ", efetivadaEm=" + efetivadaEm + "]";
	}

	public Transacao toModel(EstabelecimentoRepository estabelecimentoRepository) {
		return new Transacao(id, 
				valor,
				estabelecimento.toModel(estabelecimentoRepository), 
				cartao.toModel(), 
				efetivadaEm);
	}

}
