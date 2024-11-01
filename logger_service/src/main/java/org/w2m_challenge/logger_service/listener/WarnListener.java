package org.w2m_challenge.logger_service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WarnListener {
    @RabbitListener(queues = "queue-warn")
    public void receiveMessage(String content) {
        log.warn(content);
    }
}

