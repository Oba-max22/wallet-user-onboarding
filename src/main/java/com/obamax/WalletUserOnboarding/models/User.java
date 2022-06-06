package com.obamax.WalletUserOnboarding.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="users")
public class User extends BaseEntity {

    @NotBlank(message = "name can not be blank")
    @Column(name = "last_name", nullable = false)
    private String name;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Email(message = "email must have valid format")
    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
            name="users_roles",
            joinColumns= {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;
}
