import  {React} from 'react';
import  {Link} from 'react-router-dom';
import './MatchSmallCard.scss'

export const MatchSmallCard = ({teamName, match}) => {
    if (!match) return null;
    const otherTeam = match.home === teamName ? match.away : match.home
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.winner;
  return (
    <div className={isMatchWon ? "MatchSmallCard won-card" : "MatchSmallCard lost-card"}>
      <span className='vs'>vs</span>
      <h1><Link to={otherTeamRoute}>{otherTeam}</Link></h1>
        <p>{match.home} {match.hgoals}-{match.agoals} {match.away}</p>
    </div>
  );
}