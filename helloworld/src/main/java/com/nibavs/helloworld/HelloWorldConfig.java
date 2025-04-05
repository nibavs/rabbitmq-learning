package com.nibavs.helloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"hello-world"})
@Configuration
public class HelloWorldConfig {
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("reciever")
    @Bean
    public Tut1Reciever reciever() {
        return new Tut1Reciever();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }

}
