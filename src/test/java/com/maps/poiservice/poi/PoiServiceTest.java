package com.maps.poiservice.poi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maps.poiservice.model.poi.PointOfInterest;
import com.maps.poiservice.model.poi.PointOfInterestType;
import com.maps.poiservice.model.zone.Zone;
import com.maps.poiservice.zone.ZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiServiceTest {

    private static final double LATITUDE = 40.0;
    private static final double LONGITUDE = 50.0;
    private static final Zone ZONE = Zone.PARIS;
    private static final String POIS_AS_JSON = "poisAsJson";
    private static final PointOfInterestType POI_TYPE = PointOfInterestType.RESTAURANT;
    private static final List<PointOfInterest> EXPECTED_POIS = createMockPois();

    @Mock
    private ZoneService zoneService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PoiService poiService;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        when(zoneService.getZone(LATITUDE, LONGITUDE)).thenReturn(ZONE);
        when(valueOperations.get(ZONE.name())).thenReturn(POIS_AS_JSON);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void shouldReturnFilteredPois() throws Exception {
        // given
        when(objectMapper.readValue(eq(POIS_AS_JSON), any(TypeReference.class))).thenReturn(EXPECTED_POIS);

        // when
        List<PointOfInterest> actualPois = poiService.getPois(LATITUDE, LONGITUDE, POI_TYPE);

        // then
        assertEquals(1, actualPois.size());
        PointOfInterest expectedPoi = new PointOfInterest("Some restaurant", PointOfInterestType.RESTAURANT);
        assertEquals(expectedPoi, actualPois.get(0));
    }

    @Test
    public void shouldReturnAllPois() throws Exception {
        // given
        when(objectMapper.readValue(eq(POIS_AS_JSON), any(TypeReference.class))).thenReturn(EXPECTED_POIS);

        // when
        List<PointOfInterest> actualPois = poiService.getPois(LATITUDE, LONGITUDE, null);

        // then
        assertThat(actualPois).hasSameElementsAs(EXPECTED_POIS);
    }

    @Test
    public void shouldReturnEmptyPoisList() throws Exception {
        // given
        when(objectMapper.readValue(eq(POIS_AS_JSON), any(TypeReference.class))).thenReturn(Collections.emptyList());

        // when
        List<PointOfInterest> actualPois = poiService.getPois(LATITUDE, LONGITUDE, null);

        // then
        assertThat(actualPois).isEmpty();
    }

    private static List<PointOfInterest> createMockPois() {
        List<PointOfInterest> pois = new ArrayList<>();
        pois.add(new PointOfInterest("Some restaurant", PointOfInterestType.RESTAURANT));
        pois.add(new PointOfInterest("Some museum", PointOfInterestType.MUSEUM));
        return pois;
    }
}
