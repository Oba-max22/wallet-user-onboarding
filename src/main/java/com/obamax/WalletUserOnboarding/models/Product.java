package com.obamax.WalletUserOnboarding.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @NotBlank(message = "name can not be blank")
    @Column(name = "last_name", nullable = false)
    public String name;

    @NotBlank(message = "price can not be blank")
    @Column(name = "price", nullable = false)
    public double price;
}
