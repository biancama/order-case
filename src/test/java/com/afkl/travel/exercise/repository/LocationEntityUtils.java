package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.CountryEntity;

import static liquibase.repackaged.org.apache.commons.lang3.RandomUtils.nextDouble;
import static liquibase.repackaged.org.apache.commons.lang3.RandomUtils.nextInt;

public enum LocationEntityUtils {
    INSTANCE;

    public CountryEntity createCountryEntity(String code) {
        var countryEntity = new CountryEntity();
        countryEntity.setId(nextInt());
        countryEntity.setCode(code);
        countryEntity.setLatitude(nextDouble());
        countryEntity.setLongitude(nextDouble());
        return countryEntity;
    }
}
