package com.nibavs.rabbitmqlearning.workqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"hello-world"})
@Configuration
public class WorkQueueConfig {
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("reciever")
    @Bean
    public WorkQueueReceiver reciever() {
        return new WorkQueueReceiver(1);
    }

    @Profile("sender")
    @Bean
    public WorkQueueSender sender() {
        return new WorkQueueSender();
    }

}
