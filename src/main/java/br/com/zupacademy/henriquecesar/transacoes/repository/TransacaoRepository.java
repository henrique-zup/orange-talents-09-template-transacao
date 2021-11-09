package br.com.zupacademy.henriquecesar.transacoes.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;

public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, String> {

	List<Transacao> findByCartaoId(String cartaoId, Pageable paginacao);

}
