package com.learning.path.service;

import com.learning.path.dto.DashboardDTO;
import java.util.Map;

public interface DashboardService {
    DashboardDTO getUserDashboard(Long userId);
    Map<String, Object> getUserAnalytics(Long userId);
}