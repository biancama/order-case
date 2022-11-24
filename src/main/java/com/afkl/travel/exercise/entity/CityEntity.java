package com.afkl.travel.exercise.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("city")
public class CityEntity extends LocationEntity {
}
