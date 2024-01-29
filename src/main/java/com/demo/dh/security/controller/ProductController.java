package com.demo.dh.security.controller;

import com.demo.dh.security.model.entity.Product;
import com.demo.dh.security.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = this.productService.findAllProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody @Validated Product productRequest) {
        Product product = this.productService.createOne(productRequest);

        return ResponseEntity.ok(product);
    }
}
