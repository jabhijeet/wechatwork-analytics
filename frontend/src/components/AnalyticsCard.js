import React, {Component} from 'react';
import {Card, CardBody, CardHeader, CardText} from 'reactstrap';

const colors = [
    '#FF5733',
    '#7DCEA0',
    '#3498DB',
    '#85929E',
    '#FAD7A0',
    '#F5B7B1',
    '#C39BD3',
];


class AnalyticsCard extends Component {
    constructor(props) {
        super(props);
        let number = Math.floor((Math.random() * colors.length));
        this.state = {
            id: props.id,
            color: colors[number],
            header: props.header,
            title: props.title,
            api: props.api,
            text: 'Loading...',
            loading: true
        };
        this.getOutput = this.getOutput.bind(this);
    }

    async componentDidMount() {
        let response = await fetch(this.state.api);
        let body = await response.json();
        console.log(body);
        var state = {
            text: this.getOutput(body),
            loading: false
        };
        this.setState(state);
    }

    getOutput(body) {
        let id = this.state.id;
        if (id === "TotalDepartments") {
            return body.department.length;
        } else if (id === "TotalCompanyClients") {
            var totalClients = 0;
            for (const [key, value] of Object.entries(body)) {
                totalClients = totalClients + value;
            }
            return totalClients;
        } else if (id === "TotalCompanyEmployees") {
            return body.follow_user.length;
        }
        return body;
    }

    render() {
        return (
            <Card body style={{backgroundColor: this.state.color}}>
                <CardHeader style={{backgroundColor: '#0192'}}>{this.state.header}</CardHeader>
                <CardBody>
                    {this.state.loading ? 'Loading...' :
                        <CardText style={{color: "#89827", fontSize: 45}}>{this.state.text}</CardText>}
                </CardBody>
            </Card>
        );

    }

}

export default AnalyticsCard;