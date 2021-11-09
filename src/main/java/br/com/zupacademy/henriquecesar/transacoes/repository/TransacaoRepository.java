package br.com.zupacademy.henriquecesar.transacoes.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;

public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, String> {

}
