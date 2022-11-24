package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.AirportEntity;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;

import java.util.Optional;

public interface AirportRepository extends LocationRepository<AirportEntity> {
    default Optional<Location> findSpecificDTOByCodeTypeAndLanguage(String code, String language){
        return findDTOByCodeTypeAndLanguage(code, LocationType.AIRPORT.toString(), language);
    }
}
