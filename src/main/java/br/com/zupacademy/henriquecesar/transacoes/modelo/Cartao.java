package br.com.zupacademy.henriquecesar.transacoes.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zupacademy.henriquecesar.transacoes.util.Criptografia;

@Entity
public class Cartao {
	
	@Id
	private String id;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@JsonIgnore
	private String emailHash;

	@Deprecated
	public Cartao() {
	}

	public Cartao(String id, @NotBlank String email) {
		this.id = id;
		this.email = Criptografia.criptografar(email);
		this.emailHash = Criptografia.hash(email);
	}
	
	public String getId() {
		return id;
	}

	public String getEmail() {
		return Criptografia.descriptografar(email);
	}
}
