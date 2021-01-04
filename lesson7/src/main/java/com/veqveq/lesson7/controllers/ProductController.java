package com.veqveq.lesson7.controllers;

import com.veqveq.lesson7.models.Product;
import com.veqveq.lesson7.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/find_all")
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    @GetMapping("/delete/{id}")
    public void deleteByID(@PathVariable Long id){
        productRepository.deleteById(id);
    }

    @GetMapping ("/expensive/{minCost}")
    public List<Product> findExpensive(@PathVariable int minCost){
        return productRepository.findAllByCostGreaterThan(minCost);
    }

    @GetMapping ("/cheaper/{maxCost}")
    public List<Product> findCheaper(@PathVariable int maxCost){
        return productRepository.findAllByCostLessThan(maxCost);
    }

    @GetMapping ("/between")
    public List<Product> findBetween(@RequestParam(name = "min") int minCost, @RequestParam(name = "max") int maxCost){
        return productRepository.findAllByCostBetween(minCost, maxCost);
    }

    @PostMapping ("/save")
    public void save(@RequestParam Long id, @RequestParam String title, @RequestParam int cost){
        productRepository.saveAndFlush(new Product(id,title,cost));
    }
}
