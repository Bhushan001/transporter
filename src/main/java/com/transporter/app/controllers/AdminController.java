package com.transporter.app.controllers;

import com.transporter.app.entity.Role;
import com.transporter.app.entity.User;
import com.transporter.app.repository.UserRepository;
import com.transporter.app.services.RoleService;
import com.transporter.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // @PreAuthorize("hasAuthority('Role_ADMIN')")
    @PostMapping("/admin/add-user")
    public String addUserByAdmin(@RequestBody User user){
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        for (Role role: user.getRoles()) {}
//        Role role = new Role();
//        for (Role inputRole : user.getRoles()) {
//            role.setRole(inputRole.getRole());
//        }
//        roleService.save(role);
        user.setPassword(encryptedPassword);
        User savedUser = userService.save(user);
        return "User Added Successfully !";
    }

    @PreAuthorize("hasAuthority('Role_ADMIN')")
    @GetMapping("/admin/hello")
    public String hello() {
        return "Hello World";
    }
}
