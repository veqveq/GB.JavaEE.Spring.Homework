package com.veqveq.lesson7.repositories;

import com.veqveq.lesson7.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long> {
    List<Product> findAllByCostGreaterThan(int minCost);
    List<Product> findAllByCostLessThan(int maxCost);
    List<Product> findAllByCostBetween(int minCost, int maxCost);
}
