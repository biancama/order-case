package com.afkl.travel.exercise.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationEntity {
    @Id
//    @GeneratedValue
    protected int id;
    @NotNull
    protected String code;

    protected Double longitude;

    protected Double latitude;

    @Column(name="type", insertable = false, updatable = false)
    protected String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    protected LocationEntity parent;
}
