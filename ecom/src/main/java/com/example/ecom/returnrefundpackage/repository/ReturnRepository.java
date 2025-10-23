package com.example.Authentication.Repositories;

import com.example.Authentication.Entities.ReturnRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReturnRepository extends JpaRepository<ReturnRequest, Long> {
    List<ReturnRequest> findByUserEmail(String userEmail);
}
