package dev.purbanowicz.scheduler.controller;

import dev.purbanowicz.scheduler.dto.RegisterDto;
import dev.purbanowicz.scheduler.entity.Role;
import dev.purbanowicz.scheduler.entity.UserEntity;
import dev.purbanowicz.scheduler.repository.RoleRepository;
import dev.purbanowicz.scheduler.repository.UserRepository;
import dev.purbanowicz.scheduler.security.RolesConstants;
import dev.purbanowicz.scheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = roleRepository.findByName("USER").get();
        user.setRoles(List.of(role));
        userRepository.save(user);
        System.out.println("ZAREJESTROWANO");
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/workers")
    public ResponseEntity<List<UserEntity>> findByRole() {
        return ResponseEntity.ok(userService.findByRole(RolesConstants.USER));
    }


}
