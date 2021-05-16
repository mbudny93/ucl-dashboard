import  {React} from 'react';
import  {Link} from 'react-router-dom';
import './MatchDetailCard.scss'

export const MatchDetailCard = ({teamName, match}) => {
    if (!match) return null;
    const otherTeam = match.home === teamName ? match.away : match.home
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.winner;
      return (
        <div className={isMatchWon ? "MatchDetailCard won-card" : "MatchDetailCard lost-card"}>
          <span className='vs'>vs</span>
            <h1>
              <Link to={otherTeamRoute}>{otherTeam}</Link>
            </h1>

            <h2 className='match-date'>{match.home} {match.hgoals}-{match.agoals} {match.away}</h2>
            <h3 className='match-stage'>Stage: {match.stage}</h3>
            <h3 className='match-venue'>{match.date}</h3>
            <h3 className='match-result'>{match.location}</h3>
        </div>
      );
}

