package com.budnma.ucldashboard.repository;

import com.budnma.ucldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeamRepository extends CrudRepository<Team, UUID> {

    Team findByName(String teamName);
}
