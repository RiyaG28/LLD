package org.example.managements;

import org.example.model.Event;
import org.example.model.Team;
import org.example.model.TimeSlot;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.*;

public class TeamManagement {

    private static TeamManagement instance;

    private Map<String, Team> teamMap;

    private TeamManagement(){
        teamMap= new HashMap<>();
    }

    public static TeamManagement getInstance(){

        if(instance==null){
            synchronized (TeamManagement.class){
                if(instance==null){
                    instance= new TeamManagement();
                }
            }
        }

        return instance;
    }

    public void createTeam(Team team){
        teamMap.put(team.getName(), team);
    }

    public Team getTeam(String name){
        return teamMap.get(name);
    }

    public List<Team> createTeamList(String... teamNames){

        List<String> teamNameList= Arrays.asList(teamNames);

        List<Team> list= new ArrayList<>();

        for(String s:teamNameList){
            list.add(instance.getTeam(s));
        }

        return list;

    }

    public List<TimeSlot> getAvailableSlots(String name, LocalDateTime start, LocalDateTime end, int representation) {

        List<TimeSlot> ans = new ArrayList<>();

        Team team = getTeam(name);

        int rep=0;

        for (User user : team.getT()) {

            TreeSet<Event> events = user.getEvent();

            start= start.isAfter(user.getTimeSlot().getStartTime()) ? start : user.getTimeSlot().getStartTime();
            end=end.isBefore(user.getTimeSlot().getEndTime()) ? end : user.getTimeSlot().getEndTime();

            LocalDateTime currStart = start;


            for (Event e : events) {

                LocalDateTime es = e.getTimeSlot().getStartTime();
                LocalDateTime ee = e.getTimeSlot().getEndTime();

                if (es.isAfter(currStart)) {
                    ans.add(new TimeSlot(start, ee));
                    currStart = ee;
                } else {
                    currStart = ee;
                }
            }


            if (currStart.isBefore(end)) {
                ans.add(new TimeSlot(currStart, end));
            }
        }

            return ans;

        }



}
