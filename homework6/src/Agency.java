import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class Agency{

    public static void main(String[] argv) throws Exception {
        String name = argv[0];
        // info
        System.out.println(name);

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = "exchange1";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        int counter = 0;

        while (true) {

            // read msg
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter message[(P)eople. (C)argo, (S)atellite]: ");
            String message = br.readLine();

            if ("exit".equals(message)) {
                break;
            }
            String routingKey = message;
            message = message + " - " + name + " - " + counter;

            if(Set.of("P", "C", "S").contains(routingKey) )
            {
                // publish
                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
                System.out.println(name + " sent: " + message);
                counter++;
            } else {
                System.out.println("Wrong message!");
            }


        }
    }
}
