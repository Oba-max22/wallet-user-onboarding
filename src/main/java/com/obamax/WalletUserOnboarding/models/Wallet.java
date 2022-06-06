package com.obamax.WalletUserOnboarding.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
}
