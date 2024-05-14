package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

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

        System.out.println(response);

        System.out.println(response.getTickets().get(0).getDurationFlight().getSeconds() + "\n");

        calculateMinTime(response.getTickets());

        calculateAvgBetweenMedian(response.getTickets());




    }

    //Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика
    public static void calculateMinTime(List<Ticket> tickets){

        Stream<Ticket> ticketStream = tickets.stream();


       // Map<String, List<Duration>> mapDuration=
                ticketStream
                .filter(ticket -> Objects.equals(ticket.getOrigin(), "VVO") && Objects.equals(ticket.getDestination(), "TLV"))
                .collect(Collectors.groupingBy(Ticket::getCarrier,
                        Collectors.mapping(Ticket::getDurationFlight,
                                Collectors.toList() )))
                .values()
                .stream()
                        //.sorted()
                        .forEach(System.out::println);





       // System.out.println(mapDuration);


        //    .collect(groupingBy(Ticket::getCarrier));
                //.forEach(System.out::println);
       // System.out.println(mapDuration);

//        var str = ticketStream
//                .filter(ticket -> Objects.equals(ticket.getOrigin(), "VVO") && Objects.equals(ticket.getDestination(), "TLV"))
//                .map(Ticket::getDurationFlight)
//                .toList();
//              //  .sorted()
//               // .forEach(System.out::println);

    }

    public static void calculateAvgBetweenMedian(List<Ticket> tickets){
        Stream<Ticket> ticketStream = tickets.stream();
        List<Integer> listPrice = ticketStream
                .filter(ticket -> Objects.equals(ticket.getOrigin(), "VVO") && Objects.equals(ticket.getDestination(), "TLV"))
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        var value = listPrice
                .stream()
                .mapToInt(Integer::intValue)
                .average();

    }
}