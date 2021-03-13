package com.example.eureka.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
