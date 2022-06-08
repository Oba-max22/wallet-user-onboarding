package com.obamax.WalletUserOnboarding.models;

import com.obamax.WalletUserOnboarding.models.enums.TransactionMode;
import com.obamax.WalletUserOnboarding.models.enums.TransactionStatus;
import com.obamax.WalletUserOnboarding.models.enums.TransactionType;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMode transactionMode;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private double amount;

    private String bankName;

    private String beneficiaryAccountNumber;

    private String beneficiaryAccountName;

}
