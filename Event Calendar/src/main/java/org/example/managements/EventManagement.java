package org.example.managements;

import org.example.model.Event;
import org.example.model.Team;
import org.example.model.TimeSlot;
import org.example.model.User;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class EventManagement {

    private static EventManagement instance;

    private Map<String, Event> eventMap;

    private EventManagement(){
        this.eventMap= new HashMap<>();
    }

    public static EventManagement getInstance(){

        if(instance==null){
            synchronized (EventManagement.class){
                if(instance==null){
                    instance= new EventManagement();
                }
            }
        }

        return instance;
    }

    public void addEvent(Event event){

        TimeSlot eventime = event.getTimeSlot();

        List<User> users= event.getU();
        List<Team> teams = event.getT();

        int representatives= event.getTeamRepresentation();


        for(User user: users){
            if(!willUserBeAvailable(user,eventime))
                throw new RuntimeException(user.getName() + " is busy in this slot");
        }

        for(Team team : teams){

            int n=0;
            List<User> u= team.getT();

            for(User user: u){
                if(willUserBeAvailable(user,eventime))n++;
                if(n==representatives)break;
            }

            if(n<representatives){
                throw new RuntimeException("One of member in a team is busy in this slot");
            }
        }


        for(User user: users){
            user.getEvent().add(event);
        }

        for(Team team: teams){
            for(User user: team.getT()){
                user.getEvent().add(event);
            }
        }

        eventMap.put(event.getName(), event);
    }

    public Event getEvent(String name){
      return  eventMap.get(name);
    }

    public boolean willUserBeAvailable(User user, TimeSlot time){

        TreeSet<Event> events= user.getEvent();
        LocalDateTime eventStartTime= time.getStartTime();
        LocalDateTime eventEndTime= time.getEndTime();

        Event eventBefore = events.lower(new Event("dummy", new TimeSlot(eventEndTime, eventEndTime)));
        Event eventAfter = events.higher(new Event("dummy", new TimeSlot(eventStartTime, eventStartTime)));

        boolean availableBefore = (eventBefore == null || eventBefore.getTimeSlot().getEndTime().compareTo(eventStartTime) <= 0);
        boolean availableAfter = (eventAfter == null || eventAfter.getTimeSlot().getStartTime().compareTo(eventEndTime) >= 0);

        return availableBefore && availableAfter;
    }

}
