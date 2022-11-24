package com.afkl.travel.exercise.service.impl;

import com.afkl.travel.exercise.entity.LocationEntity;
import com.afkl.travel.exercise.internationalization.LocaleResolver;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.repository.AirportRepository;
import com.afkl.travel.exercise.repository.CityRepository;
import com.afkl.travel.exercise.repository.CountryRepository;
import com.afkl.travel.exercise.repository.LocationRepository;
import com.afkl.travel.exercise.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Slf4j
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository<LocationEntity> locationRepository;
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final LocaleResolver localeResolver;

    private LocationRepository<? extends LocationEntity> getCorrectRepository(LocationType locationType) {
        LocationRepository<? extends LocationEntity> result = null;

        switch (locationType) {
            case AIRPORT:
                result = airportRepository;
                break;
            case CITY:
                result = cityRepository;
                break;
            case COUNTRY:
                result = countryRepository;
                break;
        }

        return result;
    }

    @Override
    public Optional<Location> findLocation(String code, LocationType type, HttpServletRequest request) {
        log.info(String.format("Received findLocations Request for code: %s and type %s", code, type));
        var locale = localeResolver.resolveLocale(request);
        var locationRepository = getCorrectRepository(type);
        var location = locationRepository.findSpecificDTOByCodeTypeAndLanguage(code, locale.getLanguage().toUpperCase());
        return location;
    }

    @Override
    public List<Location> findLocations(HttpServletRequest request) {
        log.info("Received findLocations Request");
        var locale = localeResolver.resolveLocale(request);
        Iterable<Location> locations = locationRepository.findDTOByLanguage(locale.getLanguage().toUpperCase());
        return StreamSupport.stream(locations.spliterator(), false)
                .collect(Collectors.toList());
    }
}
