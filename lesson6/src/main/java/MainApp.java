import beans.AppConfig;
import beans.ShopService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ShopService shopService = context.getBean("shopService",ShopService.class);
        System.out.println(shopService.findDetailsFromOrder(1,2));
        System.out.println(shopService.findProductsFromClient(1));
        System.out.println(shopService.findBuyersOfProduct(1));
        context.close();
        shopService.close();
    }
}
