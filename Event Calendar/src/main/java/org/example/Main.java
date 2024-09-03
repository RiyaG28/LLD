package org.example;

import org.example.managements.EventManagement;
import org.example.managements.TeamManagement;
import org.example.managements.UserManagement;
import org.example.model.Event;
import org.example.model.Team;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome to Event Calendar!\n");

        UserManagement userManagement= UserManagement.getInstance();
        TeamManagement teamManagement = TeamManagement.getInstance();
        EventManagement eventManagement= EventManagement.getInstance();

        LocalDateTime today = LocalDateTime.now();

        userManagement.onboard(new User(1, "A", today.withHour(10).withMinute(0), today.withHour(19).withMinute(0)));
        userManagement.onboard(new User(2, "B", today.withHour(9).withMinute(30), today.withHour(17).withMinute(30)));
        userManagement.onboard(new User(3, "C", today.withHour(11).withMinute(30), today.withHour(18).withMinute(30)));
        userManagement.onboard(new User(4, "D", today.withHour(10).withMinute(0), today.withHour(18).withMinute(0)));
        userManagement.onboard(new User(5, "E", today.withHour(11).withMinute(0), today.withHour(19).withMinute(30)));
        userManagement.onboard(new User(6, "F", today.withHour(11).withMinute(0), today.withHour(18).withMinute(30)));

        Team T1= new Team("T1", List.of(userManagement.getUser("C"),userManagement.getUser("E")));
        Team T2= new Team("T2", List.of(userManagement.getUser("B"),userManagement.getUser("D"),userManagement.getUser("F")));


        teamManagement.createTeam(T1);
        teamManagement.createTeam(T2);


        Event event1 = new Event("Event1",
                today.plusDays(1).withHour(14).withMinute(0),  // Next day
                today.plusDays(1).withHour(15).withMinute(0),  // Next day
                List.of(userManagement.getUser("A")),
                List.of(teamManagement.getTeam("T1")),
                2
        );

        Event event2= new Event("Event2",
                today.plusDays(1).withHour(14).withMinute(0),
                today.plusDays(1).withHour(15).withMinute(0),
                userManagement.createUserList("C"));

        Event event3= new Event("Event3",
                today.withHour(15),
                today.withHour(16),
                teamManagement.createTeamList("T1","T2"),
                2
                );

        Event event4= new Event("Event4",
                today.withHour(15),
                today.withHour(16),
                userManagement.createUserList("A"),
                teamManagement.createTeamList("T2"),
                1);

        Event event5= new Event("Event5",
                today.withHour(10),
                today.withHour(11),
                userManagement.createUserList("A","F"));

        List<Event> events = Arrays.asList(event1, event2, event3, event4, event5);

        for (Event event : events) {
            try {
                eventManagement.addEvent(event);
            } catch (RuntimeException e) {
                System.out.println("Failed to add event: " + event + ". Error: " + e.getMessage());
            }
        }

        User user= userManagement.getUser("A");


        System.out.println(userManagement.getEvents("A",today.withHour(10),today.plusDays(1).withHour(17)));
        System.out.println(userManagement.getEvents( "B",today.withHour(10),today.plusDays(1).withHour(17)));

        System.out.println(userManagement.getAvailableSlots("A",today.withHour(0),today.withHour(23)));
        System.out.println(teamManagement.getAvailableSlots( "T1",today.withHour(0),today.withHour(23),1));


    }
}