package br.com.zupacademy.henriquecesar.transacoes.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.henriquecesar.transacoes.dto.response.TransacaoResponse;
import br.com.zupacademy.henriquecesar.transacoes.exception.business.CartaoNaoEncontradoException;
import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;
import br.com.zupacademy.henriquecesar.transacoes.repository.CartaoRepository;
import br.com.zupacademy.henriquecesar.transacoes.repository.TransacaoRepository;

@RestController
@RequestMapping("/transacoes")
public class ListaTransacoesController {
	
	private static int PAGE_NUMBER = 0;
	private static int PAGE_SIZE = 10;
	private static Sort SORT_BY = Sort.by("dataEfetivacao").descending();
	private Pageable paginacao = PageRequest.of(PAGE_NUMBER, PAGE_SIZE, SORT_BY);
	
	private TransacaoRepository transacaoRepository;
	private CartaoRepository cartaoRepository;
	
	public ListaTransacoesController(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
		this.transacaoRepository = transacaoRepository;
		this.cartaoRepository = cartaoRepository;
	}

	@GetMapping("/{idCartao}")
	public List<TransacaoResponse> listarTransacoes(@PathVariable String idCartao) {
		cartaoRepository
			.findById(idCartao)
			.orElseThrow(CartaoNaoEncontradoException::new);
		
		List<Transacao> transacoes = transacaoRepository
				.findByCartaoId(idCartao, paginacao);
		
		List<TransacaoResponse> response = transacoes
				.stream().map(TransacaoResponse::new)
				.collect(Collectors.toList());
		
		return response;
	}

}
