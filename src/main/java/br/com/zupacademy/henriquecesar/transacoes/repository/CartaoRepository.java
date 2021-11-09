package br.com.zupacademy.henriquecesar.transacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, String> {

}
