package com.budnma.ucldashboard.data;

import com.budnma.ucldashboard.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private JdbcTemplate jdbcTemplate;
    private EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
//            printBatchResult();

            Map<String, Team> teamData = new HashMap<>();
            entityManager.createQuery("select m.home, count(*) from Match m group by m.home", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .forEach(team -> teamData.put(team.getName(), team));

            entityManager.createQuery("select m.away, count(*) from Match m group by m.away", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(elem -> {
                        Team team = teamData.get((String)elem[0]);
                        // TODO dirty hack - cover scenario when Team does not exist in map
                        if (team != null) team.setTotalMatches(team.getTotalMatches()+(long)elem[1]);
                    });

            entityManager.createQuery("select m.winner, count(*) from Match m group by m.winner", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(elem -> {
                        Team team = teamData.get((String)elem[0]);
                        if (team != null) team.setWins((long)elem[1]);
                    });

            entityManager.createQuery("select home, away from Match where winner='DRAW'", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(elem -> {
                        Team team1 = teamData.get((String)elem[0]);
                        Team team2 = teamData.get((String)elem[1]);
                        teamData.get(team1.getName()).setDraws(team1.getDraws()+1);
                        teamData.get(team2.getName()).setDraws(team2.getDraws()+1);
                    });

            teamData.values().forEach(team -> team.setLoses(team.getTotalMatches()-team.getWins()-team.getDraws()));
            teamData.values().forEach(team -> entityManager.persist(team));
            teamData.values().forEach(System.out::println);
        }
    }

    private void printBatchResult() {
        jdbcTemplate.query("SELECT home, away, date FROM match order by date DESC",
                (rs, row) -> " Home team: " + rs.getString(1) + " | Away team: " + rs.getString(2) + " | Date: " + rs.getString(3)
        ).forEach(System.out::println);
    }
}
