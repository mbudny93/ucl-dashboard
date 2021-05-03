import  {React} from 'react';

export const MatchSmallCard = ({match}) => {
  return (
    <div className="MatchSmallCard">
        <p>{match.home} vs {match.away}</p>
    </div>
  );
}

