package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.CityEntity;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;

import java.util.Optional;

public interface CityRepository extends LocationRepository<CityEntity> {
    default Optional<Location> findSpecificDTOByCodeTypeAndLanguage(String code, String language){
        return findDTOByCodeTypeAndLanguage(code, LocationType.CITY.toString(), language);
    }
}
