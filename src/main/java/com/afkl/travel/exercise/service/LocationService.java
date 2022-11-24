package com.afkl.travel.exercise.service;

import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface LocationService {

    Optional<Location> findLocation(String code, LocationType type, HttpServletRequest request);

    List<Location>  findLocations(HttpServletRequest request);
}
