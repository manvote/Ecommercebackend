package com.example.ecom.SignUpPackage.Repositories;


import com.example.ecom.SignUpPackage.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);  // login by email
}
