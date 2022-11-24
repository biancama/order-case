package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationEntityPersistUtils extends AbstractPersistUtils  {

    public LocationEntity setupPersistedCountryEntity(String code) {
        var countryEntity = LocationEntityUtils.INSTANCE.createCountryEntity(code);
        entityManager.persist(countryEntity);
        commit();
        return countryEntity;
    }
}
