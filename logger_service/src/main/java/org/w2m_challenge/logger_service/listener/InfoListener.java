package org.w2m_challenge.logger_service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InfoListener {
    @RabbitListener(queues = "queue-info")
    public void receiveMessage(String content) {
        log.info(content);
    }
}
