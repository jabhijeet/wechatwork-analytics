import React, {Component} from 'react';
import CanvasJSReact from '../canvasjs.react';


var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class GraphCard extends Component {
    constructor(props) {
        super(props);

        this.state = {
            title: props.title,
            api: props.api,
            chartType: props.chartType,
            loading: true
        };
        this.generateDataPoints = this.generateDataPoints.bind(this);

    }

    generateDataPoints(noOfDps) {
        var xVal = 1, yVal = 100;
        var dps = [];
        for (var i = 0; i < noOfDps; i++) {
            yVal = yVal + Math.round(5 + Math.random() * (-5 - 5));
            dps.push({x: xVal, y: yVal});
            xVal++;
        }
        console.log(dps);
        return dps;
    }

    render() {

        const options = {
            theme: "light1", // "light1", "dark1", "dark2"
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
                dataPoints: this.generateDataPoints(500)
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