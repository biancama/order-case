package com.afkl.travel.exercise.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class LocationDeserializer extends StdDeserializer<Location> {
    public LocationDeserializer(Class<?> vc) {
        super(vc);
    }

    public LocationDeserializer(JavaType valueType) {
        super(valueType);
    }

    public LocationDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    public LocationDeserializer() {
        super(Location.class);
    }


    @Override
    public Location deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode productNode = jp.getCodec().readTree(jp);
        return new Location(
                productNode.get("code").textValue(),
                productNode.get("name").textValue(),
                productNode.get("type").textValue(),
                productNode.get("latitude") != null ? productNode.get("latitude").asDouble(): null,
                productNode.get("longitude") != null ? productNode.get("longitude").asDouble(): null,
                productNode.get("description").textValue(),
                productNode.get("parentCode") != null ? productNode.get("parentCode").textValue(): null,
                productNode.get("parentType") != null ? productNode.get("parentType").textValue(): null
                );
    }
}
