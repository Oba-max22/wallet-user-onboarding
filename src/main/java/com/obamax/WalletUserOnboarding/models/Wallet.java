package com.obamax.WalletUserOnboarding.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wallet")
public class Wallet extends BaseEntity {

    @OneToOne(mappedBy = "wallet")
    private User user;

    @NotBlank(message = "balance can not be blank")
    @Column(name = "balance", nullable = false)
    private double balance;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactionList;
}
