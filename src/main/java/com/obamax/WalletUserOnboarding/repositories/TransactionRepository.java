package com.obamax.WalletUserOnboarding.repositories;

import com.obamax.WalletUserOnboarding.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
