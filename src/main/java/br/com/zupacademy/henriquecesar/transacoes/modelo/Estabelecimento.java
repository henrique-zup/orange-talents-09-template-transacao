package br.com.zupacademy.henriquecesar.transacoes.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Estabelecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@NotBlank
	private String nome;

	@NotBlank
	private String cidade;
	
	@NotBlank
	private String endereco;

	@Deprecated
	public Estabelecimento() {
	}

	public Estabelecimento(@NotBlank String nome, @NotBlank String cidade, @NotBlank String endereco) {
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEndereco() {
		return endereco;
	}
}
