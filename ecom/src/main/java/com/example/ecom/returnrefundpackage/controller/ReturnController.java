package com.example.Authentication.Controller;

import com.example.Authentication.Entities.ReturnRequest;
import com.example.Authentication.Service.ReturnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReturnController {
    private final ReturnService service;

    public ReturnController(ReturnService service) {
        this.service = service;
    }

    // User requests a return
    @PostMapping("/returns")
    public ReturnRequest requestReturn(@RequestBody ReturnRequest req) {
        return service.requestReturn(req);
    }

    // Admin: view all returns
    @GetMapping("/admin/returns")
    public List<ReturnRequest> getAllReturns() {
        return service.getAllReturns();
    }

    // Update status (approve / reject)
    @PutMapping("/returns/{id}")
    public ReturnRequest updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.updateStatus(id, body.get("status"));
    }
}
