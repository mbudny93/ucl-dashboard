package com.budnma.ucldashboard.data;

import com.budnma.ucldashboard.model.Match;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.util.UUID;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(UUID.randomUUID());
        match.setHome(matchInput.getHome());
        match.setAway(matchInput.getAway());
        match.setHGoals(matchInput.getHGoals());
        match.setAGoals(matchInput.getAGoals());
        match.setDate(LocalDate.parse(trim(matchInput.getFullDate())));
        match.setLocation(matchInput.getLocation());
        match.setStage(matchInput.getStage());
        match.setWinner(getWinner(matchInput));
        return match;
    }

    private String trim(String input) {
        return input.substring(0, input.indexOf(" "));
    }
    private String getWinner(MatchInput matchInput) {
        int homeGoals = Integer.parseInt(getGoalsAsNumericString(matchInput.getHGoals()));
        int awayGoals = Integer.parseInt(getGoalsAsNumericString(matchInput.getAGoals()));
        if (homeGoals > awayGoals) {
            return matchInput.getHome();
        }
        else if (homeGoals < awayGoals) {
            return matchInput.getAway();
        }
        else {
            return "DRAW";
        }
    }
    private String getGoalsAsNumericString(String goals) {
        String res = "";
        res += goals.charAt(0);
        if (goals.length() > 1 && Character.isDigit(goals.charAt(1)))
            res += goals.charAt(1);
        return res;
    }
}
