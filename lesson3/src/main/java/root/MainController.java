package root;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class MainController {

    private Repository repository;

    public MainController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public String getProducts(Model model) {
        model.addAttribute("products", repository.getProductList());
        return "products_table";
    }

    @GetMapping("/del/{id}")
    public String removeProduct(@PathVariable Integer id) {
        repository.removeProduct(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam int id, @RequestParam String title, @RequestParam float cost) {
        if (id > 0 && cost > 0) repository.addProduct(id, title, cost);
        return "redirect:/products/all";
    }
}
