package com.obamax.WalletUserOnboarding.controllers;

import com.obamax.WalletUserOnboarding.models.Product;
import com.obamax.WalletUserOnboarding.payload.requests.ProductRequest;
import com.obamax.WalletUserOnboarding.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Product> uploadProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productService.uploadProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Page<Product> productResponsePage = productService.getAllProductsPage(page, limit);
        return new ResponseEntity<>(productResponsePage, HttpStatus.OK);
    }
}
