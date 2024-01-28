package com.demo.dh.security.service.product;

import com.demo.dh.security.model.entity.Product;
import com.demo.dh.security.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product createOne(Product productDto) {
        return this.productRepository.save(productDto);
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = this.productRepository.findAll();

        if (!products.isEmpty()) {
            return products;
        }

        throw new RuntimeException("Error to find products");
    }
}
