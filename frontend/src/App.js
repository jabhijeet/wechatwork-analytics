import React, {Component} from 'react';
import './App.css';
import AnalyticsCard from "./components/AnalyticsCard";
import {CardDeck, Col, Row} from 'reactstrap';
import GraphCard from "./components/GraphCard";


class App extends Component {
    state = {
        isLoading: false,
    };


    render() {
        const {isLoading} = this.state;
        if (isLoading) {
            return "Loading...";
        }
        return (
            <div className="App">
                <header className="App-header">WeChatWork-Analytics</header>

                <CardDeck>
                    <AnalyticsCard header={'Total Departments'} api={'/greeting?name=Departments'}/>
                    <AnalyticsCard header={'Total Company Employees'}
                                   api={'/greeting?name=Employees'}/>
                    <AnalyticsCard header={'Total Company Clients'} api={'/greeting?name=Clients'}/>
                    <AnalyticsCard header={'Active Clients'} api={'/greeting?name=ActiveClients'}/>
                    <AnalyticsCard header={'TypesOfMessages'} api={'/greeting?name=TypesOfMessages'}/>
                </CardDeck>
                <Row><Col><GraphCard title={'Hourly Usage'} chartType={'area'}/></Col><Col><GraphCard  title={'Response Times'} chartType={'column'}/></Col></Row>
            </div>
        );
    }
}

export default App;