import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import './TeamPage.scss'
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom'

export const TeamPage = () => {
    const [team, setTeam] = useState({ matches: [] });
    const { teamName } = useParams();
    const goBackText = '< Home'
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`)
                const data = await response.json();
                setTeam(data);
            }
            fetchMatches();
        }, [teamName]
    );

    if (!team || !team.name)
        return <h1>Team not found</h1>

    return (
        <div>
            <div className='go-home-link'>
                <Link to={`/`}> {goBackText} </Link>
            </div>
            <div className="TeamPage">
                <div className='team-name-section'>
                    <h1 className='team-name'>{team.name}</h1>
                </div>
                <div className='win-loss-section'>
                    Wins/Losses
            <PieChart
                        data={[
                            { title: 'Wins', value: team.wins, color: '#4da375' },
                            { title: 'Draws', value: team.draws, color: '#fff' },
                            { title: 'Losses', value: team.loses, color: '#a34d5d' },
                        ]}
                    />
                </div>
                <div className='match-detail-section'>
                    <h3>Latest Matches</h3>
                    <MatchDetailCard teamName={team.name} match={team.matches[0]} />
                </div>
                {team.matches.slice(1).map(match => <MatchSmallCard teamName={team.name} match={match} />)}
                <div className='moreLink'>
                    <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More></Link>
                </div>
            </div>

        </div>
    );
}

