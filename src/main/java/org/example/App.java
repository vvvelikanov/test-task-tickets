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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\tickets.json";
        File file = new File(path);

        TypeReference<JsonResponseByTickets<Ticket>> typeRef = new TypeReference<>() {};
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);

        JsonResponseByTickets<Ticket> response = objectMapper.readValue(file, typeRef);


        calculateMinTime(response.getTickets());

        System.out.println(calculateAvgBetweenMedian(response.getTickets()));
    }

    //Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика
    public static void calculateMinTime(List<Ticket> tickets){

        tickets.stream()
                .filter(ticket -> Objects.equals(ticket.getOrigin(), "VVO") && Objects.equals(ticket.getDestination(), "TLV"))
                .collect(Collectors.groupingBy(Ticket::getCarrier,
                        Collectors.mapping(Ticket::getDurationFlight,
                                Collectors.toList())))
                        .forEach((key, value) -> {System.out.println(
                                "carrier: " + key + " min time: " + value.stream()
                                        .sorted()
                                        .toList()
                                        .get(0));});
    }

    //Разница между средней ценой и медианой для полета между городами  Владивосток и Тель-Авив
    public static String  calculateAvgBetweenMedian(List<Ticket> tickets){
        Stream<Ticket> ticketStream = tickets.stream();
        List<Integer> listPrice =
                ticketStream
                .filter(ticket -> Objects.equals(ticket.getOrigin(), "VVO") && Objects.equals(ticket.getDestination(), "TLV"))
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        double avg = listPrice
                .stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();

        double median = listPrice.get(listPrice.size()/2);
        if (listPrice.size()%2 == 0)
            median = (median + listPrice.get(listPrice.size()/2-1)) / 2;

        return Double.toString(Math.abs(avg-median));
    }
}