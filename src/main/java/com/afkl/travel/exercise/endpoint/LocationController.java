package com.afkl.travel.exercise.endpoint;

import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping(value = "/locations/{type}/{code}")
    public ResponseEntity<Location> getLocations(HttpServletRequest request,
                                                 @PathVariable("type") String locationTypeStr,
                                                 @PathVariable("code") String code)  {
        log.info(String.format("GET /locations/%s/%s", locationTypeStr, code));
        var locationType = LocationType.valueOf(locationTypeStr.toUpperCase());
        return locationService.findLocation(code, locationType, request).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/locations")
    public Iterable<Location> getLocations(HttpServletRequest request){
        log.info(String.format("GET /locations"));
        return locationService.findLocations(request);
    }
}
