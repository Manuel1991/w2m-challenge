package org.w2m_challenge.logger_service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ErrorListener {
    @RabbitListener(queues = "queue-error")
    public void receiveMessage(String content) {
        log.error(content);
    }
}
