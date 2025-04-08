package com.nibavs.rabbitmqlearning.topics;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RpcExampleServer {
    @RabbitListener(queues = "tut.rpc.requests")
    public int fibonacci(int n) {
        System.out.println(" [x] Received request for " + n);
        int result = fib(n);
        System.out.println(" [.] Returned " + result);
        return result;
    }

    public int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }
}
