package com.demobank.atm.port.adapter.events.common;

import org.apache.kafka.clients.admin.NewTopic;
import org.jmolecules.architecture.hexagonal.Adapter;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.demobank.atm.domain.model.common.DomainEvent;
import com.demobank.atm.domain.model.common.DomainEventListener;

@Component
@Adapter
public class KafkaDomainEventListener implements DomainEventListener {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final String topicName;

	public String getTopicName() {
        return topicName;
    }

    public KafkaDomainEventListener(KafkaTemplate<String, DomainEvent> kafkaTemplate, @Value("${spring.kafka.template.default-topic:demobank_events}") String topicName) {
		this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        kafkaTemplate.setDefaultTopic(this.getTopicName());
	}

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(this.getTopicName())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @EventListener
    @DomainEventHandler
    public void handleDomainEvent(DomainEvent event) {
        this.kafkaTemplate.sendDefault(event);
    }
}
