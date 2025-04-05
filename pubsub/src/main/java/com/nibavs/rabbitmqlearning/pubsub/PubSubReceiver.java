package com.nibavs.rabbitmqlearning.pubsub;

import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@NoArgsConstructor
public class PubSubReceiver {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        receive(message, 1);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void receive2(String message) throws InterruptedException {
        receive(message, 2);
    }


    public void receive(String message, int receiver) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("instance " + receiver + " [x] Received '" + message + "'");
        doWork(message);
        stopWatch.stop();
        System.out.println("instance " + receiver + " [x] Done in " + stopWatch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String message) throws InterruptedException {
        for (char c : message.toCharArray()) {
            if (c == '.') {
                Thread.sleep(500);
            }
        }
    }

}
