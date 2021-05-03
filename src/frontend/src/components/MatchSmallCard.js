import  {React} from 'react';
import  {Link} from 'react-router-dom';

export const MatchSmallCard = ({teamName, match}) => {
    if (!match) return null;
    const otherTeam = match.home === teamName ? match.away : match.home
    const otherTeamRoute = `/teams/${otherTeam}`;
  return (
    <div className="MatchSmallCard">
        <p>vs
            <Link to={otherTeamRoute}>{otherTeam}</Link></p>
        <p>{match.home} {match.hgoals}-{match.agoals} {match.away}</p>
    </div>
  );
}

