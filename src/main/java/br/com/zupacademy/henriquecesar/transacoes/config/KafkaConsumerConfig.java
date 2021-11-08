package br.com.zupacademy.henriquecesar.transacoes.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.zupacademy.henriquecesar.transacoes.consumer.response.TransacaoConsumerResponse;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private KafkaProperties kafkaProperties;

	public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	public Map<String, Object> consumerConfigProperties() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getKeyDeserializer());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getValueDeserializer());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());
		return props;
	}

	@Bean
	public ConsumerFactory<String, TransacaoConsumerResponse> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigProperties(), 
				new StringDeserializer(),
				new JsonDeserializer<>(TransacaoConsumerResponse.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TransacaoConsumerResponse> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TransacaoConsumerResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
