package com.learning.path.controller;

import com.learning.path.dto.DashboardDTO;
import com.learning.path.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<DashboardDTO> getUserDashboard(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getUserDashboard(userId));
    }

    @GetMapping("/analytics/{userId}")
    public ResponseEntity<Map<String, Object>> getUserAnalytics(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getUserAnalytics(userId));
    }
}