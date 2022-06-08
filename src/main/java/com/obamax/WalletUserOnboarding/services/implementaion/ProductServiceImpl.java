package com.obamax.WalletUserOnboarding.services.implementaion;

import com.obamax.WalletUserOnboarding.models.Product;
import com.obamax.WalletUserOnboarding.payload.requests.ProductRequest;
import com.obamax.WalletUserOnboarding.repositories.ProductRepository;
import com.obamax.WalletUserOnboarding.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product uploadProduct(ProductRequest productRequest) {
        return productRepository.save(Product.builder().name(productRequest.getName())
                .price(productRequest.getPrice())
                .build());
    }

    @Override
    public Page<Product> getAllProductsPage(int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);
        return productRepository.findAll(pageable);
    }
}
