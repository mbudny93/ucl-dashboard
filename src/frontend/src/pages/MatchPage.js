import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom'
import { MatchDetailCard } from '../components/MatchDetailCard';
import { YearSelector } from '../components/YearSelector';
import './MatchPage.scss'

export const MatchPage = () => {

    const [matches, setMatches] = useState([]);
    const { teamName, year } = useParams();
    const goBackText = '< Back'

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
            }
            fetchMatches();
        }, [teamName, year]
    );

    return (
        <div>
            <div className='go-back-link'>
                <Link to={`/teams/${teamName}`}> {goBackText}</Link>
            </div>
            <div className="MatchPage">
                <div className="year-selector">
                    <h3> Select Year </h3>
                    <YearSelector teamName={teamName} />
                </div>
                <div>
                    <h1 className="page-heading">{teamName} matches in {year}</h1>
                    {
                        matches.map(match => <MatchDetailCard teamName={teamName} match={match} />)
                    }
                </div>
            </div>
        </div>
    );
}

