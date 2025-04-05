package com.nibavs.rabbitmqlearning.topics;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"topics"})
@Configuration
public class TopicsConfig {
    @Bean
    public TopicExchange topic() {
        return new TopicExchange("top.topic");
    }

    @Profile("receiver")
    private static class RecieverConfig {
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.orange.*");
        }

        @Bean
        public Binding binding1b(TopicExchange topic, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#");
        }

        @Bean
        public TopicsReceiver receiver() {
            return new TopicsReceiver();
        }
    }

    @Profile("sender")
    @Bean
    public TopicsSender sender() {
        return new TopicsSender();
    }

}
