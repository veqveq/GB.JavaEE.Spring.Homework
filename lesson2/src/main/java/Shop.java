import beans.AppConfig;
import beans.Cart;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Shop {

    private static Cart cart;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        cart = context.getBean("cart", Cart.class);
        System.out.println("Products \n" + cart.getProductRepository().getProductList());

        Scanner sc = new Scanner(System.in);
        String command;
        do {
            command = sc.nextLine();
        } while (commandHandler(command));
    }

    public static boolean commandHandler(String command) {
        if (command.contains("exit")) {
            System.exit(0);
            return false;
        }
        if (!command.contains("get") && !command.contains("del") && !command.contains("print")) {
            System.out.println("Unknown command");
            return true;
        }
        if (command.contains("get")) {
            int id = getId(command);
            cart.add(id);
        } else if (command.contains("del")) {
            int id = getId(command);
            cart.del(id);
            System.out.println(String.format("Product %d removed in cart",id));
        }
        if (command.contains("print")) {
            System.out.println("Cart \n" + cart.getCartList());
        }
        return true;
    }

    private static int getId(String command) {
        try {
            StringBuilder id = new StringBuilder();
            char[] symbols = command.toCharArray();
            for (char c : symbols) {
                if (c >= 48 && c <= 57) {
                    id.append(c);
                } else if (id.length() > 0) {
                    return Integer.parseInt(id.toString());
                }
            }
            return Integer.parseInt(id.toString());
        } catch (NumberFormatException e) {
            System.out.println("Id number not found");
            return -1;
        }
    }
}
