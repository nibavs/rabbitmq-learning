package com.nibavs.rabbitmqlearning.topics;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"rpc"})
@Configuration
public class RpcConfig {
    @Profile("client")
    private static class ClientConfig {
        @Bean
        public DirectExchange exchange() {
            return new DirectExchange("tut.rpc");
        }

        @Bean
        public RpcExampleClient client() {
            return new RpcExampleClient();
        }
    }

    @Profile("server")
    private static class ServerConfig {
        @Bean
        public Queue queue() {
            return new Queue("tut.rpc.requests");
        }

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange("tut.rpc");
        }

        @Bean
        public Binding binding(DirectExchange exchange,
                               Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with("rpc");
        }

        @Bean
        public RpcExampleServer server() {
            return new RpcExampleServer();
        }
    }

}
