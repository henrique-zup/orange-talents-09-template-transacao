package br.com.zupacademy.henriquecesar.transacoes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupacademy.henriquecesar.transacoes.modelo.Cartao;
import br.com.zupacademy.henriquecesar.transacoes.modelo.Estabelecimento;
import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@TestInstance(Lifecycle.PER_CLASS)
class ListaTransacoesControllerTest {
	
	@Autowired
	private MockMvc mvc;	

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private final String URI = "/transacoes";
	
	private Cartao cadastraCartao(String numeroCartao, String email) {
		Cartao cartao = new Cartao(numeroCartao, email);
		manager.persist(cartao);
		return cartao;
	}
	
	private void cadastraTransacoes(int numeroTransacoes, Cartao cartao) {
		Estabelecimento estabelecimento = new Estabelecimento("Nome", "Cidade", "Endereco");
		manager.persist(estabelecimento);
		for (int i = 1; i <= numeroTransacoes; i++) {
			String index = String.valueOf(i); 
			Transacao transacao = new Transacao(index,
					new BigDecimal(i * 100),
					estabelecimento,
					cartao,
					LocalDateTime.now().minusDays(i)
			);
			manager.persist(transacao);
		}
	}

	@ParameterizedTest
	@Transactional
	@DisplayName("Deve listar as transações mais recentes, com limite máximo de 10.")
	@CsvSource({
		"1234-4567-7889-7897, 11",
		"0000-4444-4444-1545, 3,",
		"0000-4444-4444-1111, 0"
	})
	void test1(String cartaoId, int numeroTransacoes) throws Exception {
		Cartao cartao = cadastraCartao(cartaoId, "Nome do Titular");
		cadastraTransacoes(numeroTransacoes, cartao);
		int limite = (numeroTransacoes < 10) ? numeroTransacoes : 10;
		
		MockHttpServletRequestBuilder request = 
			get(URI.concat(String.format("/%s", cartaoId)))
				.contentType(MediaType.APPLICATION_JSON);
			
		MvcResult resultado = mvc.perform(request)
			.andExpect(status().isOk())
			.andReturn();
		
		String conteudoResultado = resultado.getResponse().getContentAsString();
		Transacao[] transacoes = objectMapper.readValue(conteudoResultado, Transacao[].class);
		
		// certifica que retornaram apenas 10
		assertEquals(transacoes.length, limite);
				
		// certifica que está em ordem: mais recente para mais antiga
		LocalDateTime data = LocalDateTime.now();
		for (Transacao transacao : transacoes) {
			assertTrue(transacao.getDataEfetivacao().isBefore(data));
			data = transacao.getDataEfetivacao();
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"0000-0000-0000-0000"})
	@NullAndEmptySource
	@DisplayName("Deve retornar 404 quando não existir o cartão no Banco de Dados.")
	void test2(String cartaoId) throws Exception {
		
		MockHttpServletRequestBuilder request = 
			get(URI.concat(String.format("/%s", cartaoId)))
				.contentType(MediaType.APPLICATION_JSON);
			
		mvc.perform(request)
			.andExpect(status().isNotFound());
	}
}
