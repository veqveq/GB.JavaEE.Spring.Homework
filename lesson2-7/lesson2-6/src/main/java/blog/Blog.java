package blog;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import models.MyArticle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Blog {
    @SuppressWarnings("StringOperationCanBeSimplified")
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String msg = new String();
            while (!msg.contains("#end")) {
                msg = sc.nextLine();
                MyArticle article = new MyArticle(msg);
                if (article.isFinish()) return;
                sendMessage(channel, article);
            }
        }
    }

    private static void sendMessage(Channel channel, MyArticle article) throws IOException {
        for (String tag : article.getTags()) {
            channel.exchangeDeclare(tag, BuiltinExchangeType.DIRECT);
            channel.basicPublish(tag, tag, null, article.getContent().getBytes(StandardCharsets.UTF_8));
            System.out.println("Send[x] " + tag + ": " + article.getContent());
        }
    }
}
