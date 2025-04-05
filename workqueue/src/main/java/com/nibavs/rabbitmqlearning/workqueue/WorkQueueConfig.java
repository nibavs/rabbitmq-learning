package com.nibavs.rabbitmqlearning.workqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"work-queue"})
@Configuration
public class WorkQueueConfig {
    @Bean
    public Queue workQueue() {
        return new Queue("work-queue");
    }

    @Profile("receiver")
    private static class RecieverConfig {
        @Bean
        public WorkQueueReceiver receiver1() {
            return new WorkQueueReceiver(1);
        }
        @Bean
        public WorkQueueReceiver receiver2() {
            return new WorkQueueReceiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public WorkQueueSender sender() {
        return new WorkQueueSender();
    }

}
