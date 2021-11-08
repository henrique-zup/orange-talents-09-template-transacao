package br.com.zupacademy.henriquecesar.transacoes.consumer.response;

public class CartaoConsumerResponse {

	private String id;
	private String email;

	public CartaoConsumerResponse() {
	}

	public CartaoConsumerResponse(String id, String email) {
		this.id = id;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "CartaoConsumerResponse [id=" + id + ", email=" + email + "]";
	}
}
