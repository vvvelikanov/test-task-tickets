package org.example;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    String origin;
    private String destination;
    private String carrier;
    private int price;

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    public Duration getDurationFlight(){
        return Duration.between(departureDateTime, arrivalDateTime);
    }

    @JsonCreator
    public Ticket(
            @JsonProperty("departure_date") LocalDate departureDate,
            @JsonProperty("departure_time")LocalTime departureTime,
            @JsonProperty("arrival_date") LocalDate arrivalDate,
            @JsonProperty("arrival_time") LocalTime arrivalTime){
        this.departureDateTime = departureDate.atTime(departureTime);
        this.arrivalDateTime = arrivalDate.atTime(arrivalTime);
    }

    @JsonAnySetter
    public void allSetter(String fieldName, String fieldValue) {
        unrecognizedFields.put(fieldName, fieldValue);
    }

    private Map<String, String> unrecognizedFields = new HashMap<>();

    @Override
    public String toString() {
        return "{origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", carrier='" + carrier +
                ", durationFlight=" + getDurationFlight() +
                ", price='" + price +
                '}';
    }
}

