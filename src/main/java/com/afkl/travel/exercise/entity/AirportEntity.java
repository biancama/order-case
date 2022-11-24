package com.afkl.travel.exercise.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("airport")
public class AirportEntity extends LocationEntity {
}
