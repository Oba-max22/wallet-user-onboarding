package com.obamax.WalletUserOnboarding.repositories;

import com.obamax.WalletUserOnboarding.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
