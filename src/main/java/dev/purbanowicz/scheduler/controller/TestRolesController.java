package dev.purbanowicz.scheduler.controller;

import dev.purbanowicz.scheduler.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestRolesController {

    @GetMapping("/all")
    public ResponseEntity<String> allRoles(Authentication authentication) {
        return ResponseEntity.ok(authentication.getAuthorities().toString());
    }
}
