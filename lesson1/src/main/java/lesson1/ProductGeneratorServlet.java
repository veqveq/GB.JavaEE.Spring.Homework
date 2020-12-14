package lesson1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "product_generator", urlPatterns = "/product_list")
public class ProductGeneratorServlet extends HttpServlet {

    private int lastId = 0;
    private Logger logger = LoggerFactory.getLogger(ProductGeneratorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(createProductList(10));
        lastId = 0;
    }

    private String createProductList(int size) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Product currentProduct = generateProduct();
            logger.info("New product {} created", currentProduct.toString());
            list.append(String.format("<p>%d | %s | %.02f</p>", currentProduct.getId(), currentProduct.getTitle(), currentProduct.getCoast()))
                    .append("\n");
        }
        return list.toString();
    }

    private Product generateProduct() {
        String title = "product " + (int) ((Math.random() * 99) + 1);
        Double coast = Math.random() * 999 + 1;
        lastId += 1;
        return new Product(lastId, title, coast);
    }
}
