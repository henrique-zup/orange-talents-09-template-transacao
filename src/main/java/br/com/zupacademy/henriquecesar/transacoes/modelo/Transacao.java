package br.com.zupacademy.henriquecesar.transacoes.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Transacao {
	
	@Id
	private String id;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Estabelecimento estabelecimento;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;
	
	@JsonProperty("efetivadaEm")
	private LocalDateTime dataEfetivacao;

	@Deprecated
	public Transacao() {
	}

	public Transacao(@NotBlank String id, @NotNull @Positive BigDecimal valor, @NotNull Estabelecimento estabelecimento,
			@NotNull Cartao cartao, LocalDateTime dataEfetivacao) {
		this.id = id;
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.dataEfetivacao = dataEfetivacao;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getDataEfetivacao() {
		return dataEfetivacao;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", valor=" + valor + ", estabelecimento=" + estabelecimento + ", cartao="
				+ cartao + ", dataEfetivacao=" + dataEfetivacao + "]";
	}

}
