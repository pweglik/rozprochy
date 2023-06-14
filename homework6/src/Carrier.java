import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Carrier extends Thread{

    String name;
    Set<String> services;

    public Carrier(String name, Set<String> services) {
        this.name = name;
        this.services = services;
    }

    public void run () {
        try {
            main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void main() throws Exception {

        // info
        System.out.println(this.name);

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = "exchange1";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // consumer (message handling)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(name + " received: " + message);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " done processing");
            }
        };

        // queue & bind
        for(String service: services) {
            channel.queueDeclare(service, false, false, false, null);
            channel.queueBind(service, EXCHANGE_NAME, service);
            System.out.println(name + " created queue and listening on it: " + service);
            channel.basicConsume(service, true, consumer);
        }
    }
}
