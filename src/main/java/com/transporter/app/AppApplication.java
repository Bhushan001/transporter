package com.transporter.app;

import com.transporter.app.entity.Privilege;
import com.transporter.app.entity.Role;
import com.transporter.app.entity.User;
import com.transporter.app.repository.PrivilegeRepository;
import com.transporter.app.repository.RoleRepository;
import com.transporter.app.repository.UserRepository;
import com.transporter.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    private Logger logger = LoggerFactory.getLogger(AppApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//        Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
//        User user = new User();
//        user.setFirstName("Bhushan");
//        user.setLastName("Gadekar");
//        user.setPassword(passwordEncoder.encode("bhushan123"));
//        user.setEmail("bhushan@yahoo.com");
//        user.setRoles(Arrays.asList(adminRole));
//        user.setActive(true);
//        userRepository.save(user);
//        Role userRole = roleRepository.findByRole("ROLE_USER");
//        User user2 = new User();
//        user2.setFirstName("Ganesh");
//        user2.setLastName("Karki");
//        user2.setPassword(passwordEncoder.encode("ganesh123"));
//        user2.setEmail("ganesh@yahoo.com");
//        user2.setRoles(Arrays.asList(userRole));
//        user2.setActive(true);
//        userRepository.save(user2);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByRole(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
