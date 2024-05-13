package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\tickets.json";
        File file = new File(path);

        TypeReference<JsonResponseByTickets<Ticket>> typeRef = new TypeReference<JsonResponseByTickets<Ticket>>() {};
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);

        JsonResponseByTickets<Ticket> response = objectMapper.readValue(file, typeRef);


        for (Ticket ticket : response.getTickets()){ //Хззз норм ли так делать. над этим подумать
            ticket.setArrivalDateTime();
            ticket.setDepartureDateTime();
        }
        response.getTickets().get(0).setArrivalDateTime();
        System.out.println(response);






    }

    public static void calculateMinTime(){

    }

    public static void calculateAvgBetweenMedian(){

    }
}