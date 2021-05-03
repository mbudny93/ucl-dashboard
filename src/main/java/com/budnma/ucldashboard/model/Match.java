package com.budnma.ucldashboard.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Match {

    @Id @Getter @Setter
    private UUID id;
    @Getter @Setter
    private String home;
    @Getter @Setter
    private String away;
    @Getter @Setter
    private String stage;
    @Getter @Setter
    private String location;
    @Getter @Setter
    private String hGoals;
    @Getter @Setter
    private String aGoals;
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    private String winner;
}
