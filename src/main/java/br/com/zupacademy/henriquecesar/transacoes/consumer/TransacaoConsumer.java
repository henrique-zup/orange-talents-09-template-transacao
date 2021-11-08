package br.com.zupacademy.henriquecesar.transacoes.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.zupacademy.henriquecesar.transacoes.consumer.response.TransacaoConsumerResponse;

@Service
public class TransacaoConsumer {
	
	private Logger logger = LoggerFactory.getLogger(TransacaoConsumer.class);

	@KafkaListener(topics = "${topic.name.consumer}")
	public void consume(TransacaoConsumerResponse transacao) {
		logger.info(String.format("Transação: %s", transacao));
	}

}
