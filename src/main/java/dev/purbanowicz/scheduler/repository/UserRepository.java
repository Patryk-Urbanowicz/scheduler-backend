package dev.purbanowicz.scheduler.repository;

import dev.purbanowicz.scheduler.entity.Role;
import dev.purbanowicz.scheduler.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    List<UserEntity> findByRoles(List<Role> roles);

    boolean existsByUsername(String username);
}
