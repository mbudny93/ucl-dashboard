package com.budnma.ucldashboard.repository;

import com.budnma.ucldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {
    List<Match> getByHomeOrAwayOrderByDateDesc(String home, String away, Pageable pageable);

    default List<Match> findLatestMatches(String teamName, int count) {
        return getByHomeOrAwayOrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
