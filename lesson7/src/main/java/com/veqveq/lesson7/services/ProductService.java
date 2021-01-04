package com.veqveq.lesson7.services;

import com.veqveq.lesson7.models.Product;
import com.veqveq.lesson7.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void save(Long id, String title, int cost) {
        productRepository.saveAndFlush(new Product(id, title, cost));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findExpensive(int minCost) {
        return productRepository.findAllByCostGreaterThan(minCost);
    }

    public List<Product> findCheaper(int maxCost) {
        return productRepository.findAllByCostLessThan(maxCost);
    }

    public List<Product> findBetween(int minCost, int maxCost) {
        return productRepository.findAllByCostBetween(minCost, maxCost);
    }
}