package com.obamax.WalletUserOnboarding.models;

import com.obamax.WalletUserOnboarding.models.enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleType type;
}