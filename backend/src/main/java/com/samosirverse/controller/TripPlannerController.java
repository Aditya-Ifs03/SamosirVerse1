package com.samosirverse.controller;

import com.samosirverse.dto.ApiResponse;
import com.samosirverse.dto.TripPlanRequest;
import com.samosirverse.dto.TripPlanResponse;
import com.samosirverse.service.TripPlannerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trip-planner")
public class TripPlannerController {

    private final TripPlannerService tripPlannerService;

    public TripPlannerController(TripPlannerService tripPlannerService) {
        this.tripPlannerService = tripPlannerService;
    }

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<TripPlanResponse>> generate(@Valid @RequestBody TripPlanRequest request) {
        TripPlanResponse response = tripPlannerService.generatePlan(request);
        return ResponseEntity.ok(ApiResponse.ok("Rencana perjalanan cerdas Samosir berhasil disusun", response));
    }
}
