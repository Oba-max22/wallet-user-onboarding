package com.obamax.WalletUserOnboarding.repositories;

import com.obamax.WalletUserOnboarding.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
