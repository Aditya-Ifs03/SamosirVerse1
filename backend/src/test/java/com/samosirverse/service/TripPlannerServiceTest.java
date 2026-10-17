package com.samosirverse.service;

import com.samosirverse.dto.TripPlanRequest;
import com.samosirverse.dto.TripPlanResponse;
import com.samosirverse.repository.DestinationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TripPlannerServiceTest {

    @Mock
    private DestinationRepository destinationRepository;

    private TripPlannerService tripPlannerService;

    @BeforeEach
    void setUp() {
        tripPlannerService = new TripPlannerService(destinationRepository);
    }

    @Test
    void shouldGenerateValidTwoDayCulturalTripPlan() {
        TripPlanRequest request = new TripPlanRequest();
        request.setDurationDays(2);
        request.setTravelStyle("BUDAYA");
        request.setBudgetCategory("STANDAR");
        request.setGroupType("PASANGAN");

        TripPlanResponse response = tripPlannerService.generatePlan(request);

        assertNotNull(response);
        assertEquals(2, response.getDurationDays());
        assertEquals("BUDAYA", response.getTravelStyle());
        assertEquals(2, response.getDays().size());

        // Validate Day 1
        TripPlanResponse.DayPlan day1 = response.getDays().get(0);
        assertEquals(1, day1.getDayNumber());
        assertFalse(day1.getActivities().isEmpty());

        // Validate Day 2
        TripPlanResponse.DayPlan day2 = response.getDays().get(1);
        assertEquals(2, day2.getDayNumber());
        assertFalse(day2.getActivities().isEmpty());

        // Validate costs & tips
        assertTrue(response.getTotalEstimatedCost().compareTo(BigDecimal.ZERO) > 0);
        assertFalse(response.getCulturalEtiquetteTips().isEmpty());
    }

    @Test
    void shouldGenerateOneDayNatureTripPlan() {
        TripPlanRequest request = new TripPlanRequest();
        request.setDurationDays(1);
        request.setTravelStyle("ALAM");
        request.setBudgetCategory("HEMAT");
        request.setGroupType("SOLO");

        TripPlanResponse response = tripPlannerService.generatePlan(request);

        assertNotNull(response);
        assertEquals(1, response.getDurationDays());
        assertEquals(1, response.getDays().size());
        assertTrue(response.getPlanTitle().contains("Panorama Kaldera"));
    }
}
