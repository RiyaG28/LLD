package org.example.managements;

import com.sun.source.tree.Tree;
import org.example.model.Event;
import org.example.model.Team;
import org.example.model.TimeSlot;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.*;

public class UserManagement {

    private static UserManagement instance;
    private Map<String, User> userMap;

    private UserManagement(){
        userMap= new HashMap<>();
    }

    public static UserManagement getInstance(){
        if(instance==null){
             synchronized (UserManagement.class){
                if(instance==null){
                    instance= new UserManagement();
                }
            }
        }

        return instance;
    }


    public void onboard(User user){
        userMap.put(user.getName(), user);
    }

    public User getUser(String name){
        return userMap.get(name);
    }

    public List<User> createUserList(String... userNames){

        List<String> userNameList= Arrays.asList(userNames);

        List<User> list= new ArrayList<>();

        for(String s:userNameList){
            list.add(instance.getUser(s));
        }

        return list;
    }

    public List<TimeSlot> getAvailableSlots(String name, LocalDateTime start, LocalDateTime end){

        List<TimeSlot> ans= new ArrayList<>();

        User user=getUser(name);

        start= start.isAfter(user.getTimeSlot().getStartTime()) ? start : user.getTimeSlot().getStartTime();
        end=end.isBefore(user.getTimeSlot().getEndTime()) ? end : user.getTimeSlot().getEndTime();

        TreeSet<Event> events= user.getEvent();

        LocalDateTime currStart=start;

        for(Event e: events){

            LocalDateTime es= e.getTimeSlot().getStartTime();
            LocalDateTime ee= e.getTimeSlot().getEndTime();

            if(es.isAfter(currStart)) {
                ans.add(new TimeSlot(start, ee));
                currStart=ee;
            }
            else{
                currStart=ee;
            }
        }

        if (currStart.isBefore(end)) {
            ans.add(new TimeSlot(currStart, end));
        }

        return ans;
    }

    public List<Event> getEvents(String name, LocalDateTime start, LocalDateTime end){

        List<Event> ans= new ArrayList<>();
        User user= getUser(name);

        TreeSet<Event> allEvents= user.getEvent();


        for(Event e: allEvents){

            LocalDateTime eStart= e.getTimeSlot().getStartTime();
            LocalDateTime eEnd= e.getTimeSlot().getEndTime();

            if(eStart.isAfter(start) && eEnd.isBefore(end))ans.add(e);
        }
        return ans;
    }
}
