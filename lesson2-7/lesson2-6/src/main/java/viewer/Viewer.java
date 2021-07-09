package viewer;

import com.rabbitmq.client.*;
import models.MyCommand;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Viewer {

    private static List<String> subscribes;
    private static String queueName;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        subscribes = new ArrayList<>();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        queueName = channel.queueDeclare().getQueue();
        MyCommand cmd = null;
        while (cmd == null || !cmd.isFinished()) {
            try {
                cmd = new MyCommand(sc.nextLine());
                switch (cmd.getCommand()) {
                    case Subscribe:
                        subscribe(channel, cmd.getTags());
                        break;
                    case Unsubscribe:
                        unsubscribe(channel, cmd.getTags());
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Command creation error");
            }
        }
        channel.close();
        connection.close();
    }

    private static void subscribe(Channel channel, List<String> tags) throws IOException {
        for (String tag : tags) {
            if (!subscribes.contains(tag)) {
                channel.exchangeDeclare(tag, BuiltinExchangeType.DIRECT);
                subscribes.add(tag);
                channel.queueBind(queueName, tag, tag);
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println("Post[" + tag + "]: " + message);
                };
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
                });
                System.out.println("You subscribed in tag " + tag);
            } else {
                System.out.println("You already subscribe in tag " + tag);
            }
        }
    }

    private static void unsubscribe(Channel channel, List<String> tags) throws IOException {
        for (String tag : tags) {
            if (subscribes.contains(tag)) {
                channel.queueUnbind(queueName, tag, tag);
                subscribes.remove(tag);
                System.out.println("You are unsubscribed in tag " + tag);
            } else {
                System.out.printf("Subscribe %s not found%n", tag);
            }
        }
    }
}
