package br.com.zupacademy.henriquecesar.transacoes.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.zupacademy.henriquecesar.transacoes.consumer.response.TransacaoConsumerResponse;
import br.com.zupacademy.henriquecesar.transacoes.modelo.Transacao;
import br.com.zupacademy.henriquecesar.transacoes.repository.EstabelecimentoRepository;
import br.com.zupacademy.henriquecesar.transacoes.repository.TransacaoRepository;

@Service
public class TransacaoConsumer {
	
	private Logger logger = LoggerFactory.getLogger(TransacaoConsumer.class);
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	private EstabelecimentoRepository estabelecimentoRepository;
	
	public TransacaoConsumer(TransacaoRepository transacaoRepository,
			EstabelecimentoRepository estabelecimentoRepository) {
		this.transacaoRepository = transacaoRepository;
		this.estabelecimentoRepository = estabelecimentoRepository;
	}

	@KafkaListener(topics = "${topic.name.consumer}")
	public void consume(TransacaoConsumerResponse response) {
		logger.info(String.format("Transação: %s", response));
		
		Transacao transacao = response.toModel(estabelecimentoRepository);
		transacaoRepository.save(transacao);
	}

}
