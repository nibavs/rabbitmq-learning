package com.nibavs.rabbitmqlearning.routing;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class RoutingSender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    AtomicInteger count = new AtomicInteger(0);

    AtomicInteger index = new AtomicInteger(0);

    private final String[] keys = {"orange", "black", "green"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (index.incrementAndGet() == 3) {
            index.set(0);
        }
        String key = keys[index.get()];

        builder.append(key).append(' ');
        builder.append(count.incrementAndGet());
        String message = builder.toString();
        template.convertAndSend(direct.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }



}
