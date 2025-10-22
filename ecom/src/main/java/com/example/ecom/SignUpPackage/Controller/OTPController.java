package com.example.ecom.SignUpPackage.Controller;


import com.example.ecom.SignUpPackage.Entities.OTP;
import com.example.ecom.SignUpPackage.Entities.User;
import com.example.ecom.SignUpPackage.Repositories.OTPRepository;
import com.example.ecom.SignUpPackage.Repositories.UserRepository;
import com.example.ecom.SignUpPackage.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public Map<String,String> sendOtp(@RequestParam String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));

        String otpCode = String.valueOf(new Random().nextInt(900000)+100000);
        OTP otp = new OTP();
        otp.user = user;
        otp.otpCode = otpCode;
        otp.expiresAt = LocalDateTime.now().plusMinutes(10);
        otpRepository.save(otp);

        emailService.send(email,"Your OTP Code","Your OTP is: "+otpCode);
        return Map.of("message","OTP sent successfully");
    }

    @PostMapping("/verify")
    public Map<String,String> verifyOtp(@RequestParam String email, @RequestParam String otpCode){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));
        OTP otp = otpRepository.findTopByUserOrderByExpiresAtDesc(user)
                .orElseThrow(()->new RuntimeException("OTP not found"));
        if(otp.isUsed || otp.expiresAt.isBefore(LocalDateTime.now()) || !otp.otpCode.equals(otpCode)){
            throw new RuntimeException("Invalid or expired OTP");
        }
        otp.isUsed = true;
        otpRepository.save(otp);
        user.isVerified = true;
        userRepository.save(user);
        return Map.of("message","OTP verified successfully");
    }
}
