package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yy");

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext text)
            throws IOException {
        return LocalDate.parse(p.getValueAsString(), formatter);
    }
}
