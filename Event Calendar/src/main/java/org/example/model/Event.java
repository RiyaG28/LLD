package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Event implements Comparable<Event>{

    private String name;
    private TimeSlot timeSlot;
    private List<User> u;
    private List<Team> t;
    private int teamRepresentation;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, List<User> u, List<Team> t, int rep) {
        this.name = name;
        this.u = (u==null) ? Collections.emptyList(): u;
        this.t = (t==null) ? Collections.emptyList() :t;
        this.teamRepresentation= rep;
        this.timeSlot= new TimeSlot(startTime,endTime);
    }

    public Event(String name, TimeSlot t){
        this.name=name;
        this.timeSlot=t;

    }

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, List<User> u){
        this.name = name;
        this.u = u;
        this.t = Collections.emptyList();
        this.teamRepresentation= 0;
        this.timeSlot= new TimeSlot(startTime,endTime);
    }

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, List<Team> t, int rep){
        this.name = name;
        this.u = Collections.emptyList();
        this.t = t;
        this.teamRepresentation= rep;
        this.timeSlot= new TimeSlot(startTime,endTime);
    }
    @Override
    public int compareTo(Event other) {
        return this.timeSlot.getStartTime().compareTo(other.getTimeSlot().getStartTime());
    }

    @Override
    public String toString(){
        return "Event{" +
                "name='" + name + '\'' +
                ", timeSlot=" + timeSlot +
                ", users=" + u.stream().map(User::getName).collect(Collectors.toList()) +
                ", teams=" + t.stream().map(Team::getName).collect(Collectors.toList()) +
                ", teamRepresentation=" + teamRepresentation +
                '}';
    }
}
