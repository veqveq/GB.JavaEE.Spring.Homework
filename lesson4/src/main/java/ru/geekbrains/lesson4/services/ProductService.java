package ru.geekbrains.lesson4.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson4.models.Product;
import ru.geekbrains.lesson4.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    public void add(long id, String title, float cost) {
        if (id == 0) {
            for (Product p : productRepository.getProductList()) {
                if (p.getId() > id) {
                    id = p.getId();
                }
            }
            id++;
        }
        productRepository.add(new Product(id,title,cost));
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
