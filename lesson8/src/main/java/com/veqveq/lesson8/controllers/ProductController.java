package com.veqveq.lesson8.controllers;

import com.veqveq.lesson8.models.Page;
import com.veqveq.lesson8.models.Product;
import com.veqveq.lesson8.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/find")
    private Collection<Product> find(@RequestParam(name = "min", defaultValue = "0") Integer minCost,
                                     @RequestParam(name = "max", required = false) Integer maxCost,
                                     @RequestParam(name = "numb",defaultValue = "1") Integer number,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size,
                                     @RequestParam(name = "id", required = false) Long id) {
        if (id != null) {
            return Collections.singleton(productService.findById(id));
        } else {
            List<Product> products;
            if (maxCost == null) {
                maxCost = Integer.MAX_VALUE;
            }
            products = productService.findAll(minCost, maxCost);
            return new Page<>(number, size, products).getEntities();
        }
    }

    @GetMapping("/pagesCount")
    private int countPages(@RequestParam byte pagination){
        return productService.countPages(pagination);
    }

    @GetMapping("/delete/{id}")
    private void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/save")
    private void save(@RequestBody Product product) {
        product.setId(null);
        productService.save(product);
    }
}
