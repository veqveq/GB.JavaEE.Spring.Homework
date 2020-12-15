package ru.geekbrains.lesson4.contorllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson4.services.ProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.getProductList());
        return "products";
    }

    @GetMapping("/del/{id}")
    public String deleteById(@PathVariable long id) {
        productService.deleteById(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(name = "id", required = false) String id, @RequestParam String title, @RequestParam float cost) {
        long newId;
        if (id == "") {
            newId = 0;
        } else {
            newId = Long.parseLong(id);
        }
        if (newId > 0 && cost > 0) {
            productService.add(newId, title, cost);
        }
        return "redirect:/products/all";
    }
}
