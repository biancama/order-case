package com.afkl.travel.exercise.service.impl;

import com.afkl.travel.exercise.internationalization.LocaleResolver;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.repository.AirportRepository;
import com.afkl.travel.exercise.repository.CityRepository;
import com.afkl.travel.exercise.repository.CountryRepository;
import com.afkl.travel.exercise.repository.LocationRepository;
import com.afkl.travel.exercise.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;
import static liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static liquibase.repackaged.org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


/**
 * Tests for {@link LocationServiceImpl}.
 */
public class LocationServiceImplTest {
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private AirportRepository airportRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private LocaleResolver localeResolver;

    private LocationService locationService = new LocationServiceImpl(locationRepository, airportRepository, cityRepository, countryRepository, localeResolver);
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        locationService = new LocationServiceImpl(locationRepository, airportRepository, cityRepository, countryRepository, localeResolver);
    }

    @Test
    @DisplayName("/locations should return an array of locations")
    public void givenSomeLocationThenTheyAreReturned() throws Exception {
        Iterable<Location> returnedLocations = asList(
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build(),
                Location.builder().name(randomAlphanumeric(5)).code(randomAlphanumeric(5)).type(randomAlphanumeric(5)).description(randomAlphanumeric(5)).latitude(nextDouble()).build()
                );

        List<Location> expectedLocations = new ArrayList<Location>();
        returnedLocations.forEach(expectedLocations::add);
        given(localeResolver.resolveLocale(any(HttpServletRequest.class))).willReturn(new Locale("nl"));
        given(locationRepository.findDTOByLanguage("NL")).willReturn(returnedLocations);

        List<Location> actualLocations = locationService.findLocations(new MockHttpServletRequest());
        assertThat(actualLocations).containsExactlyInAnyOrderElementsOf(expectedLocations);
    }

}
