package com.niq.personalizeddata.services;

import com.niq.personalizeddata.models.Product;
import com.niq.personalizeddata.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public String addProduct(Product product) {
        return productRepo.save(product).getProductId();
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
