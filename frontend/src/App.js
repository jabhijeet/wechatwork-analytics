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
                    <AnalyticsCard header={'Total Departments'} api={'/api/v1/wechatwork/analytics/getDepartments'}
                                   id={"TotalDepartments"}/>
                    <AnalyticsCard header={'Total Company Employees'}
                                   api={'/api/v1/wechatwork/analytics/getMembersList'}
                                   id={"TotalCompanyEmployees"}/>
                    <AnalyticsCard header={'Total Company Clients'}
                                   api={'/api/v1/wechatwork/analytics/getCustomerUserMapping'}
                                   id={"TotalCompanyClients"}/>
                    <AnalyticsCard header={'Unassigned Customers'}
                                   api={'/api/v1/wechatwork/analytics/getUnassignedCustomerCount'}
                                   id={"UnassignedCustomers"}/>
                </CardDeck>
                <Row>
                    <Col>
                        <GraphCard title={'Daily Message Count'} chartType={'splineArea'} id={"message_cnt"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>
                    <Col>
                        <GraphCard title={'Daily Chat Count'} chartType={'splineArea'} id={"chat_cnt"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>

                    <Col>
                        <GraphCard title={'Daily Reply Percentage'} chartType={'column'} id={"reply_percentage"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>

                    <Col>
                        <GraphCard title={'Avg Reply Time'} chartType={'column'} id={"avg_reply_time"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <GraphCard title={'Daily Negative Feedback Count'} chartType={'column'} id={"negative_feedback_cnt"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>
                    <Col>
                        <GraphCard title={'Daily New Apply Count'} chartType={'splineArea'} id={"new_apply_cnt"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>

                    <Col>
                        <GraphCard title={'Daily New Contact Count'} chartType={'splineArea'} id={"new_contact_cnt"}
                                   api={'/api/v1/wechatwork/analytics/getStatisticsData'}/>
                    </Col>
                    <Col>
                        <GraphCard title={'Customers per Employee'} chartType={'column'} id={"EmployeeCustomers"}
                                   api={'/api/v1/wechatwork/analytics/getCustomerUserMapping'}/>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default App;