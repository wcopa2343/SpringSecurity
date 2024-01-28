package com.demo.dh.security.service.product;

import com.demo.dh.security.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product createOne(Product productDto);

    List<Product> findAllProducts();
}
