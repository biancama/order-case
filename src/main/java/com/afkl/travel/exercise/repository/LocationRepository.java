package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.LocationEntity;
import com.afkl.travel.exercise.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface LocationRepository<T extends LocationEntity> extends Repository<T, Integer> {
    @Query("select new com.afkl.travel.exercise.model.Location(l.code, t.name, l.type, l.latitude, l.longitude, t.description, p.code, p.type) " +
            "from TranslationEntity t "+
            "inner Join t.location l " +
            "left join l.parent p " +
            "where l.code = ?1 " +
            "and l.type = ?2 " +
            "and t.language = ?3")
    Optional<Location> findDTOByCodeTypeAndLanguage(String code, String type, String language);

    @Query("select new com.afkl.travel.exercise.model.Location(l.code, t.name, l.type, l.latitude, l.longitude, t.description, p.code, p.type) " +
            "from TranslationEntity t "+
            "inner Join t.location l " +
            "left join l.parent p " +
            "where t.language = ?1")
    Iterable<Location> findDTOByLanguage(String language);
    @Query("select new com.afkl.travel.exercise.model.Location(l.code, t.name, l.type, l.latitude, l.longitude, t.description, p.code, p.type) " +
            "from TranslationEntity t "+
            "inner Join t.location l " +
            "left join l.parent p " +
            "where l.code = ?1 " +
            "and t.language = ?2 ")
    Optional<Location> findSpecificDTOByCodeTypeAndLanguage(String code, String language);
}
