package com.obamax.WalletUserOnboarding.configurations;

import com.obamax.WalletUserOnboarding.models.Role;
import com.obamax.WalletUserOnboarding.models.RoleType;
import com.obamax.WalletUserOnboarding.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role role;

        if (roleRepository.findRoleByType(RoleType.ADMIN).isEmpty()) {
            role = new Role();
            role.setType(RoleType.ADMIN);
            roleRepository.save(role);
        }

        if (roleRepository.findRoleByType(RoleType.USER).isEmpty()) {
            role = new Role();
            role.setType(RoleType.USER);
            roleRepository.save(role);
        }
    }
}