package com.example.ecom.SignUpPackage.Repositories;


import com.example.ecom.SignUpPackage.Entities.OTP;
import com.example.ecom.SignUpPackage.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findTopByUserOrderByExpiresAtDesc(User user);
}
