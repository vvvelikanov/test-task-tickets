package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("H:mm");

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext text)
            throws IOException {
        return LocalTime.parse(p.getValueAsString(), formatter);

    }
}
