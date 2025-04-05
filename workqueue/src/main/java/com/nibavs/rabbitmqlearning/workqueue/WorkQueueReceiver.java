package com.nibavs.rabbitmqlearning.workqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "work-queue")
public class WorkQueueReceiver {

    private final int instance;

    public WorkQueueReceiver(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("instance " + this.instance +
                " [x] Received '" + message + "'");
        doWork(message);
        stopWatch.stop();
        System.out.println("instance " + this.instance +
                " [x] Done in " + stopWatch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String message) throws InterruptedException {
        for (char c : message.toCharArray()) {
            if (c == '.') {
                Thread.sleep(500);
            }
        }
    }

}
