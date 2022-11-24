package com.afkl.travel.exercise.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("country")
public class CountryEntity extends LocationEntity {
}
