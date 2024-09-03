package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TimeSlot {

    private LocalDateTime startTime;   //working hours
    private LocalDateTime endTime;

    public TimeSlot(LocalDateTime s, LocalDateTime e){
        this.startTime=s;
        this.endTime=e;
    }

    public String toString(){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return startTime.format(formatter) + " -> "+ endTime.format(formatter);
    }
}
