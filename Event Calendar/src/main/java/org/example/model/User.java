package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Getter
@Setter
@ToString
public class User {

    private int id;
    private String name;
    private TimeSlot timeSlot;
    private TreeSet<Event> event;

    public User(int id, String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.timeSlot= new TimeSlot(startTime,endTime);
        this.event= new TreeSet<>();
    }
}
