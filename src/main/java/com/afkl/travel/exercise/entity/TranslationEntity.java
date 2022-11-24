package com.afkl.travel.exercise.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Translation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class TranslationEntity {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String language;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private LocationEntity location;

}
