package com.obamax.WalletUserOnboarding.repositories;

import com.obamax.WalletUserOnboarding.models.Role;
import com.obamax.WalletUserOnboarding.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByType(RoleType type);
}
