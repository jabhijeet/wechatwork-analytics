import React, {Component} from 'react';
import CanvasJSReact from '../canvasjs.react';


var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class GraphCard extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: props.id,
            title: props.title,
            api: props.api,
            chartType: props.chartType,
            dataPoints: props.dataPoints,
            xValueFormatString: props.xValueFormatString,
            loading: true
        };

        this.getApiData = this.getApiData.bind(this);
    }


    async componentDidMount() {
        if (this.state.api) {
            let response = await fetch(this.state.api);
            let body = await response.json();
            console.log(body);
            var state = {response: body, loading: false};
            this.setState(state);
        }
    }

    getApiData() {
        let id = this.state.id;
        var dps = [];
        if (this.state.response) {
            var index = 1;
            for (const [key, value] of Object.entries(this.state.response)) {
                var xVal;
                var yVal;
                if (id === "EmployeeCustomers") {
                    xVal = key;
                    yVal = value;
                } else {
                    xVal = key;
                    yVal = this.state.response[key][id];
                }
                dps.push({x: index, label: xVal, y: yVal});
                index = index + 1;
            }
        }
        console.log(dps);
        return dps;
    }

    render() {

        const options = {
            theme: "light2", // "light1", "dark1", "dark2"
            animationEnabled: true,
            zoomEnabled: true,

            title: {
                fontFamily: '-apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji"',
                fontSize: 20,
                text: this.state.title
            },
            axisY: {
                includeZero: false
            },
            data: [{
                type: this.state.chartType,
                dataPoints: this.state.dataPoints ? this.state.dataPoints : this.state.api ? this.getApiData() : this.generateDataPoints(30)
            }]
        }

        return (
            <div>
                <CanvasJSChart options={options}/>
            </div>
        );
    }
}

export default GraphCard;