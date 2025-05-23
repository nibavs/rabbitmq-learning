package com.nibavs.rabbitmqlearning.pubsub;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"pub-sub", "publish/subscribe"})
@Configuration
public class PubSubConfig {
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("pubsub.fanout");
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
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public PubSubReceiver receiver() {
            return new PubSubReceiver();
        }


    }

    @Profile("sender")
    @Bean
    public PubSubSender sender() {
        return new PubSubSender();
    }

}
