package com.budnma.ucldashboard.repository;

import com.budnma.ucldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {
    List<Match> getByHomeOrAwayOrderByDateDesc(String home, String away, Pageable pageable);

    @Query("select m from Match m where (m.home = :teamName or m.away = :teamName) and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(
            @Param("teamName") String teamName,
            @Param("dateStart") LocalDate dateStart,
            @Param("dateEnd") LocalDate dateEnd
    );

    default List<Match> findLatestMatches(String teamName, int count) {
        return getByHomeOrAwayOrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
