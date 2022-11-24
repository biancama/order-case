package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.LocationEntity;
import com.afkl.travel.exercise.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(
        loader = SpringBootContextLoader.class)
@ComponentScan
@DataJpaTest
public class LocationRepositoryIT {
    @Autowired
    protected TestEntityManager entityManager;

    @Autowired
    private LocationEntityPersistUtils locationEntityPersistUtils;

    @Autowired
    private LocationRepository locationRepository;
    @Test
    public void findLocationShouldReturnTheNetherlands() {
        LocationEntity extraCountry = locationEntityPersistUtils.setupPersistedCountryEntity("CCCC");

        Iterable<Location> entities = locationRepository.findDTOByLanguage("NL");
        List<String> result =
                StreamSupport.stream(entities.spliterator(), false)
                        .map(l-> l.getCode())
                        .collect(Collectors.toList());
        assertThat(result).contains("NL");

    }
}
