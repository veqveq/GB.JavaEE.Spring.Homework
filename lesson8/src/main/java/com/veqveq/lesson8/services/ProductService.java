package com.veqveq.lesson8.services;

import com.veqveq.lesson8.models.Product;
import com.veqveq.lesson8.repositories.ProductRepository;
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

    public void save(Product product) {
        productRepository.saveAndFlush(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findAll(int minCost, int maxCost) {
        return productRepository.findAllByCostBetween(minCost, maxCost);
    }

    public int countPages(byte pagination) {
        int size = productRepository.findAll().size();
        if (size % pagination != 0) {
            return size / pagination + 1;
        } else {
            return size / pagination;
        }
    }
}