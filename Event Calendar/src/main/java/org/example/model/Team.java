package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Team {

    private String name;
    private List<User> T;

    public Team(String name, List<User> t) {
        this.name = name;
        T = t;
    }
}
