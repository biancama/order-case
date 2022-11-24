package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.CountryEntity;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;

import java.util.Optional;

public interface CountryRepository extends LocationRepository<CountryEntity> {
    default Optional<Location> findSpecificDTOByCodeTypeAndLanguage(String code, String language){
        return findDTOByCodeTypeAndLanguage(code, LocationType.COUNTRY.toString(), language);
    }
}
