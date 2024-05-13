package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class JsonResponseByTickets<T> {
    private ArrayList<T> tickets;

    @Override
    public String toString() {
        return "tickets : " + tickets;
    }
}
