package br.com.zupacademy.henriquecesar.transacoes.consumer.response;

public class EstabelecimentoConsumerResponse {

	private String nome;
	private String cidade;
	private String endereco;

	@Deprecated
	public EstabelecimentoConsumerResponse() {
	}

	public EstabelecimentoConsumerResponse(String nome, String cidade, String endereco) {
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

	@Override
	public String toString() {
		return "EstabelecimentoConsumerResponse [nome=" + nome + ", cidade=" + cidade + ", endereco=" + endereco + "]";
	}
}
