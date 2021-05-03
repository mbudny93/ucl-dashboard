import  {React} from 'react';
import  {Link} from 'react-router-dom';

export const MatchDetailCard = ({teamName, match}) => {
    if (!match) return null;
    const otherTeam = match.home === teamName ? match.away : match.home
    const otherTeamRoute = `/teams/${otherTeam}`;
      return (
        <div className="MatchDetailCard">
            <h3>Latest Matches</h3>
            <h1>vs
                <Link to={otherTeamRoute}>{otherTeam}</Link></h1>
            <h2>{match.home} {match.hgoals}-{match.agoals} {match.away}</h2>
            <h3>{match.date}</h3>
            <h3>{match.location}</h3>
        </div>
      );
}

