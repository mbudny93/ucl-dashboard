package com.budnma.ucldashboard.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private UUID id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private long totalMatches;
    @Getter @Setter
    private long wins;
    @Getter @Setter
    private long draws;
    @Getter @Setter
    private long loses;
    @Transient
    @Getter @Setter
    private List<Match> matches;

    public Team(){};

    public Team(String teamName, long totalMatches) {
        this.name = teamName;
        this.totalMatches = totalMatches;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalMatches=" + totalMatches +
                ", wins=" + wins +
                ", draws=" + draws +
                ", loses=" + loses +
                '}';
    }
}
