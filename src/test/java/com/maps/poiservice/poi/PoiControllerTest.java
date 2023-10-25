package com.maps.poiservice.poi;

import com.maps.poiservice.model.poi.PointOfInterest;
import com.maps.poiservice.model.poi.PointOfInterestType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiControllerTest {

    @Mock
    private PoiService poiService;

    @InjectMocks
    private PoiController poiController;

    @Test
    public void shouldReturnPois() throws Exception {
        // given
        double latitude = 40.0;
        double longitude = 50.0;
        PointOfInterestType poiType = PointOfInterestType.RESTAURANT;
        List<PointOfInterest> expectedPois = createMockPois();
        when(poiService.getPois(latitude, longitude, poiType)).thenReturn(expectedPois);

        // when
        List<PointOfInterest> actualPois = poiController.getPois(latitude, longitude, poiType);

        // then
        assertEquals(expectedPois, actualPois);
    }

    public List<PointOfInterest> createMockPois() {
        List<PointOfInterest> pois = new ArrayList<>();
        pois.add(new PointOfInterest("Some restaurant", PointOfInterestType.RESTAURANT));
        return pois;
    }
}
