package com.nurtricenter.contractservices.infrastructure.messaging.kafka.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaConstant {

    public static final String INBOUND_TOPIC = "${kafka.topics.inbound}";
    public static final String KAFKA_CONSUMER_GROUP_ID = "${spring.kafka.consumer.group-id}";
    public static final String LISTENER_CONTAINER_FACTORY = "kafkaListenerContainerFactory";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "${spring.kafka.bootstrap-servers}";
    public static final String ACKS_CONFIG_VALUE = "all";
    public static final int RETRIES_QUANTITY = 3;
    public static final String OFFSET_RESET_VALUE = "earliest";
    public static final int MAX_POLL_RECORDS_VALUE = 10;
    public static final int MAX_CONCURRENCY = 3;

}
