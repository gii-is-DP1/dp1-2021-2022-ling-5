import React from "react";
import { MDBContainer } from "mdbreact";
import { Pie } from "react-chartjs-2";
import { Chart, ArcElement } from 'chart.js'
Chart.register(ArcElement);

const ChartsPage = (props: any) => {
    var frequency = props.frequency;
    // Sample data
    const data = {
        labels: Array.from(frequency.keys()),
        datasets: [
            {
                label: "Hours Studied in Geeksforgeeks",
                data: Array.from(frequency.values()),
                backgroundColor: ["blue", "green", "yellow", "pink", "orange"],
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