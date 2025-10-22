package com.example.ecom.SignUpPackage.Controller;


import com.example.ecom.SignUpPackage.Entities.User;
import com.example.ecom.SignUpPackage.Repositories.UserRepository;
import com.example.ecom.SignUpPackage.Service.JwtService;
import com.example.ecom.SignUpPackage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public Map<String,String> signup(@RequestBody User request){
        if(userRepository.existsByEmail(request.email)){
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.name = request.name;
        user.email = request.email;
        user.password = passwordEncoder.encode(request.password);
        userRepository.save(user);
        String token = jwtService.generateToken(user.email);
        return Map.of("token", token);
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody User request){
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(()->new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.password, user.password)){
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtService.generateToken(user.email);
        return Map.of("token", token);
    }
    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
