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
    '#5F6A6A',
];


class AnalyticsCard extends Component {
    constructor(props) {
        super(props);
        let number = Math.floor((Math.random() * colors.length));
        this.state = {
            color: colors[number],
            header: props.header,
            title: props.title,
            api: props.api,
            text: 'Loading...',
            loading: true
        };
    }

    async componentDidMount() {
        let response = await fetch(this.state.api);
        let body = await response.json();
        console.log(body);
        this.state = {text: body.name, loading: false};
        this.setState(this.state);
    }

    render() {
        return (
            <Card body style={{backgroundColor: this.state.color}}>
                <CardHeader style={{backgroundColor: '#0192'}}>{this.state.header}</CardHeader>
                <CardBody>
                    {this.state.loading ? 'Loading...' : <CardText>{this.state.text}</CardText>}
                </CardBody>
            </Card>
        );

    }

}

export default AnalyticsCard;