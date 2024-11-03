package dev.purbanowicz.scheduler.bootload;

import dev.purbanowicz.scheduler.entity.Role;
import dev.purbanowicz.scheduler.entity.Shift;
import dev.purbanowicz.scheduler.entity.UserEntity;
import dev.purbanowicz.scheduler.repository.RoleRepository;
import dev.purbanowicz.scheduler.repository.ShiftRepository;
import dev.purbanowicz.scheduler.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShiftRepository shiftRepository;

    @Autowired
    public CommandLineAppStartupRunner(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ShiftRepository shiftRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.shiftRepository = shiftRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Starting application");
        LOG.info("Adding roles");

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleManager = new Role();
        roleManager.setName("ROLE_MANAGER");
        roleRepository.save(roleManager);

        Role roleOwner = new Role();
        roleOwner.setName("ROLE_OWNER");
        roleRepository.save(roleOwner);

        LOG.info("Adding example users");

        UserEntity user1 = new UserEntity();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("password1"));
        user1.setEmail("user1@example.com");
        user1.setPhone("000000000");
        user1.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user1);

        UserEntity user2 = new UserEntity();
        user2.setUsername("manager");
        user2.setPassword(passwordEncoder.encode("password2"));
        user2.setEmail("user2@example.com");
        user2.setPhone("111111111");
        user2.setRoles(List.of(roleRepository.findByName("ROLE_MANAGER").get()));
        userRepository.save(user2);

        UserEntity user3 = new UserEntity();
        user3.setUsername("owner");
        user3.setPassword(passwordEncoder.encode("password3"));
        user3.setEmail("user3@example.com");
        user3.setPhone("000000000");
        user3.setRoles(List.of(roleRepository.findByName("ROLE_OWNER").get()));
        userRepository.save(user3);

        Shift shift1 = new Shift();
        shift1.setStart(LocalDateTime.of(2024, 11, 11, 8, 0, 0));
        shift1.setEnd(LocalDateTime.of(2024, 11, 11, 16, 0, 0));
        shift1.setUser(user1);
        shiftRepository.save(shift1);
    }

}
