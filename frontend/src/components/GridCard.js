import React, {Component} from 'react';
import {AgGridReact} from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';

class GridCard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            columnDefs:[{
                headerName: "Employee", field: "employee", sortable: true, filter: true
            }, {
                headerName: "No Of Customers", field: "count", sortable: true, filter: true
            }],
            rowData:[]
        }
    }

    async componentDidMount() {
        let response = await fetch('/api/v1/wechatwork/analytics/getCustomerUserMapping');
        let body = await response.json();
        console.log(body);
        var dps = [];
        for (const [key, value] of Object.entries(body)) {
            dps.push({employee: key, count: value});
        }
        var state = {
            rowData: dps,
            loading: false
        };
        this.setState(state);
    }

    render() {
        return (
            <div className="ag-theme-alpine" style={{height: '90%', width: '100%'}}>
                <AgGridReact
                    columnDefs={this.state.columnDefs}
                    rowData={this.state.rowData}>
                </AgGridReact>
            </div>
        );
    }
}

export default GridCard;