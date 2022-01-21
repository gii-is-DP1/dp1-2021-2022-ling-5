import React from "react";
import { Pie } from "react-chartjs-2";
import { Chart, ArcElement } from 'chart.js'
const { MDBContainer } = require("mdbreact");

Chart.register(ArcElement);


const ChartsPage = (props: any) => {
    var ratio = props.ratio;
    const data = {
        datasets: [
            {
                data: [ratio, 1-ratio],
                backgroundColor: ["rgba(80, 202, 228, 0.88)", "rgba(2, 71, 243, 0.88)"],
            }
        ]
    }


    return (
        <MDBContainer>
            <Pie data={data} />
        </MDBContainer>
    );
}

export default ChartsPage;