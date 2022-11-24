package com.afkl.travel.exercise.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = LocationDeserializer.class)
public class Location {
    private String code;
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;
    private String description;
    private String parentCode;
    private String parentType;
}
