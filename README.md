# Rabbitmq-learning
In this project I was learning RabbitMq official tutorial to understand the key and basic concepts of the software.
- Hello world tutorial, basic async communication between producer and receiver using single queue.
- Work queues, multiple consumers, which are sharing producers tasks.
- Publish/Subscribe pattern. It is implemented via fanout exchange, where producer publishes task to the exchange and every consumer can subscribe to the exchange.
- Routing. Here instead of using fanout, I'm using direct exchange, where I can specify binding key, which is used by sender.
- Topics is an extended version of direct exchange, where I can specify multiple binding keys.
- RPC can be used via RabbitMq as well.