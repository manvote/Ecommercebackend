package com.example.Authentication.Service;

import com.example.Authentication.Entities.ReturnRequest;
import com.example.Authentication.Repositories.ReturnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnService {
    private final ReturnRepository repo;

    public ReturnService(ReturnRepository repo) {
        this.repo = repo;
    }

    public ReturnRequest requestReturn(ReturnRequest request) {
        return repo.save(request);
    }

    public List<ReturnRequest> getUserReturns(String userEmail) {
        return repo.findByUserEmail(userEmail);
    }

    public List<ReturnRequest> getAllReturns() {
        return repo.findAll();
    }

    public ReturnRequest updateStatus(Long id, String status) {
        ReturnRequest req = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        req.status = status;
        return repo.save(req);
    }
}
