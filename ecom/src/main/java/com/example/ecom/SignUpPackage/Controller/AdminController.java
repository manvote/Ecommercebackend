package com.example.ecom.SignUpPackage.Controller;


import com.example.ecom.SignUpPackage.Entities.Admin;
import com.example.ecom.SignUpPackage.Repositories.AdminRepository;
import com.example.ecom.SignUpPackage.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;


    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody Admin request) {
        if (adminRepository.findByEmail(request.email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Admin admin = new Admin();
        admin.email = request.email;
        admin.name = request.name;
        admin.password = passwordEncoder.encode(request.password);
        adminRepository.save(admin);

        String token = jwtService.generateToken(admin.email);
        return Map.of(
                "token", token,
                "username", admin.name
        );
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Admin request) {
        Admin admin = adminRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        if (!passwordEncoder.matches(request.password, admin.password)) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtService.generateToken(admin.email);
        return Map.of("token", token);
    }


}
