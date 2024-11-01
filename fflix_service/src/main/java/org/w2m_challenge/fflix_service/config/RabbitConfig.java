package org.w2m_challenge.fflix_service.config;

import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RabbitConfig {

    public static String EXCHANGE_NAME = "fflix-exchange";

    public static String QUEUE_INFO = "queue-info";
    public static String QUEUE_WARN = "queue-warn";
    public static String QUEUE_ERROR = "queue-error";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue infoQueue() {
        return new Queue(QUEUE_INFO, true);
    }

    @Bean
    public Queue warnQueue() {
        return new Queue(QUEUE_WARN, true);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(QUEUE_ERROR, true);
    }

    @Bean
    public Binding bindInfoQueue(Queue infoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(infoQueue).to(exchange).with("info");
    }

    @Bean
    public Binding bindingWarn(Queue warnQueue, TopicExchange exchange) {
        return BindingBuilder.bind(warnQueue).to(exchange).with("warn");
    }

    @Bean
    public Binding bindingError(Queue errorQueue, TopicExchange exchange) {
        return BindingBuilder.bind(errorQueue).to(exchange).with("error");
    }
}
