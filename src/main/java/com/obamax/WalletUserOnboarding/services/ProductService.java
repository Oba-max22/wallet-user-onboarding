package com.obamax.WalletUserOnboarding.services;

import com.obamax.WalletUserOnboarding.models.Product;
import com.obamax.WalletUserOnboarding.payload.requests.ProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product uploadProduct(ProductRequest productRequest);
    Page<Product> getAllProductsPage(int page, int limit);
}
