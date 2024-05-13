package org.example;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonSetter("departure_date")
    private LocalDate departureDate;
    @JsonSetter("departure_time")
    private LocalTime  departureTime;
    @JsonSetter("arrival_date")
    private LocalDate arrivalDate;
    @JsonSetter("arrival_time")
    private LocalTime arrivalTime;

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    public void setDepartureDateTime() {
        this.departureDateTime = this.departureDate.atTime(this.departureTime);
    }

    public void setArrivalDateTime() {
        this.arrivalDateTime = this.arrivalDate.atTime(this.arrivalTime);
    }

    private Map<String, String> unrecognizedFields = new HashMap<>();

    @JsonAnySetter
    public void allSetter(String fieldName, String fieldValue) {
        unrecognizedFields.put(fieldName, fieldValue);
    }

    @Override
    public String toString() {
        return "{origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                '}';
    }
}

