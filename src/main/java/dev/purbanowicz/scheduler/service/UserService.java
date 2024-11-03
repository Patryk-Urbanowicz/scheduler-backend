package dev.purbanowicz.scheduler.service;

import dev.purbanowicz.scheduler.entity.Role;
import dev.purbanowicz.scheduler.entity.UserEntity;
import dev.purbanowicz.scheduler.repository.RoleRepository;
import dev.purbanowicz.scheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> findByRole(String roleName) {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);

        if (roleOptional.isPresent()) {
            return userRepository.findByRoles(List.of(roleOptional.get()));
        } else {
            return new ArrayList<>();
        }
    }


}
