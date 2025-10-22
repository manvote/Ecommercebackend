package com.example.ecom.SignUpPackage.Controller;


import com.example.ecom.SignUpPackage.Entities.OTP;
import com.example.ecom.SignUpPackage.Entities.User;
import com.example.ecom.SignUpPackage.Repositories.OTPRepository;
import com.example.ecom.SignUpPackage.Repositories.UserRepository;
import com.example.ecom.SignUpPackage.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/forgot")
public class ForgotPassword {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailService emailService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Step 1: Send OTP for password reset
    @PostMapping("/send")
    public Map<String, String> sendResetOtp(@RequestParam String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otpCode = String.valueOf(new Random().nextInt(900000) + 100000);
        OTP otp = new OTP();
        otp.user = user;
        otp.otpCode = otpCode;
        otp.expiresAt = LocalDateTime.now().plusMinutes(10);
        otpRepository.save(otp);

        emailService.send(email, "Password Reset OTP", "Your OTP for password reset is: " + otpCode);
        return Map.of("message", "Password reset OTP sent successfully");
    }

    // Step 2: Verify OTP and change password
    @PostMapping("/verify")
    public Map<String, String> verifyResetOtp(@RequestParam String email,
                                              @RequestParam String otpCode,
                                              @RequestParam String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<OTP> otpOptional = otpRepository.findTopByUserOrderByExpiresAtDesc(user);
        if (otpOptional.isEmpty()) throw new RuntimeException("OTP not found");

        OTP otp = otpOptional.get();
        if (otp.isUsed || otp.expiresAt.isBefore(LocalDateTime.now()) || !otp.otpCode.equals(otpCode)) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        otp.isUsed = true;
        otpRepository.save(otp);

        user.password = passwordEncoder.encode(newPassword);
        userRepository.save(user);

        return Map.of("message", "Password reset successfully");
    }
}
