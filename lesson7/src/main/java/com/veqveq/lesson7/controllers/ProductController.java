package com.veqveq.lesson7.controllers;

import com.veqveq.lesson7.models.Product;
import com.veqveq.lesson7.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/find")
    private Collection<Product> find(@RequestParam(name = "min", required = false) Integer minCost,
                                    @RequestParam(name = "max", required = false) Integer maxCost,
                                    @RequestParam(name = "id", required = false) Long id) {
        if (id != null) {
            return Collections.singleton(productService.findById(id));
        } else {
            if (minCost != null && maxCost != null) {
                return productService.findBetween(minCost, maxCost);
            } else if (minCost != null && maxCost == null) {
                return productService.findExpensive(minCost);
            } else if (minCost == null && maxCost != null) {
                return productService.findCheaper(maxCost);
            } else {
                return productService.findAll();
            }
        }
    }

    @GetMapping("/delete/{id}")
    private void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/save")
    private void save(@RequestParam Long id, @RequestParam String title, @RequestParam int cost) {
        productService.save(id,title,cost);
    }
}
