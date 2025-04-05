package com.nibavs.helloworld;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class Tut1Reciever {
    @RabbitHandler
    public void recieveMessage(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }
}
