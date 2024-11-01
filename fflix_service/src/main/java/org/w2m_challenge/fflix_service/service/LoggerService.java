package org.w2m_challenge.fflix_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.w2m_challenge.fflix_service.config.RabbitConfig;

@Service
public class LoggerService {

    @Value("${spring.application.name}")
    private String applicationName;

    private final String EXCHANGE = RabbitConfig.EXCHANGE_NAME;
    private final RabbitTemplate rabbitTemplate;

    public LoggerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void info(String message) {
        logMessage("info", message);
    }

    public void warn(String message) {
        logMessage("warn", message);
    }


    public void error(String message) {
        logMessage("error", message);
    }

    private void logMessage(String routeKey, String message) {
        rabbitTemplate.convertAndSend(EXCHANGE, routeKey, String.format("app: %s | message: %s", applicationName, message));
    }
}
