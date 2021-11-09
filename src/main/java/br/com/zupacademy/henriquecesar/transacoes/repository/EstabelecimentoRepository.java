package br.com.zupacademy.henriquecesar.transacoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
	
	Optional<Estabelecimento> findByNomeAndCidadeAndEndereco(String nome, String cidade, String endereco);

}
