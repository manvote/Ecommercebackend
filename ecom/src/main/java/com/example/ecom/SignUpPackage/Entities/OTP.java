package com.example.ecom.SignUpPackage.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="otps")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    public User user;

    public String otpCode;

    public LocalDateTime expiresAt;

    public Boolean isUsed = false;
}
